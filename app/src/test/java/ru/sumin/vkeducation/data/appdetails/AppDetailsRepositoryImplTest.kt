package ru.sumin.vkeducation.data.appdetails

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import ru.sumin.vkeducation.data.appdetails.local.AppDetailsDao
import ru.sumin.vkeducation.data.appdetails.local.AppDetailsEntity
import ru.sumin.vkeducation.data.appdetails.local.AppDetailsEntityMapper
import ru.sumin.vkeducation.domain.appdetails.AppDetails
import ru.sumin.vkeducation.domain.appdetails.Category

class AppDetailsRepositoryImplTest {

    private lateinit var appApi: AppDetailsApi
    private lateinit var dao: AppDetailsDao
    private lateinit var mapper: AppDetailsMapper
    private lateinit var entityMapper: AppDetailsEntityMapper
    private lateinit var repository: AppDetailsRepositoryImpl

    @Before
    fun setUp() {
        appApi = mock()
        dao = mock()
        mapper = mock()
        entityMapper = mock()
        repository = AppDetailsRepositoryImpl(
            appApi = appApi,
            dao = dao,
            mapper = mapper,
            entityMapper = entityMapper
        )
    }

    @Test
    fun `getAppDetails returns domain from database when entity exists`() = runTest {
        val entity = createEntity(id = "123")
        val expected = createDomain(id = "123")

        whenever(dao.getAppDetails("123")).thenReturn(flowOf(entity))
        whenever(entityMapper.toDomain(entity)).thenReturn(expected)

        val result = repository.getAppDetails("123")

        assertEquals(expected, result)
        verify(entityMapper).toDomain(entity)
        verify(appApi, never()).getAppDetails(any())
    }

    @Test
    fun `getAppDetails calls api when entity does not exist in database`() = runTest {
        val dto = createDto(id = "123")
        val domain = createDomain(id = "123")
        val entity = createEntity(id = "123")

        whenever(dao.getAppDetails("123")).thenReturn(flowOf(null))
        whenever(appApi.getAppDetails("123")).thenReturn(dto)
        whenever(mapper.toDomain(dto)).thenReturn(domain)
        whenever(entityMapper.toEntity(domain)).thenReturn(entity)

        repository.getAppDetails("123")

        verify(appApi).getAppDetails("123")
    }

    @Test
    fun `getAppDetails returns domain from api when entity does not exist in database`() = runTest {
        val dto = createDto(id = "123")
        val expected = createDomain(id = "123")
        val entity = createEntity(id = "123")

        whenever(dao.getAppDetails("123")).thenReturn(flowOf(null))
        whenever(appApi.getAppDetails("123")).thenReturn(dto)
        whenever(mapper.toDomain(dto)).thenReturn(expected)
        whenever(entityMapper.toEntity(expected)).thenReturn(entity)

        val result = repository.getAppDetails("123")

        assertEquals(expected, result)
    }

    @Test
    fun `getAppDetails saves entity to database when entity does not exist in database`() = runTest {
        val dto = createDto(id = "123")
        val domain = createDomain(id = "123")
        val entity = createEntity(id = "123")

        whenever(dao.getAppDetails("123")).thenReturn(flowOf(null))
        whenever(appApi.getAppDetails("123")).thenReturn(dto)
        whenever(mapper.toDomain(dto)).thenReturn(domain)
        whenever(entityMapper.toEntity(domain)).thenReturn(entity)

        repository.getAppDetails("123")

        verify(dao).insertAppDetails(entity)
    }

    @Test(expected = RuntimeException::class)
    fun `getAppDetails throws exception when api throws exception`() = runTest {
        whenever(dao.getAppDetails("123")).thenReturn(flowOf(null))
        whenever(appApi.getAppDetails("123")).thenThrow(RuntimeException())

        repository.getAppDetails("123")
    }

    @Test
    fun `toggleWishList updates wishlist status to true when current value is false`() = runTest {
        val entity = createEntity(id = "123", isInWishlist = false)

        whenever(dao.getAppDetails("123")).thenReturn(flowOf(entity))

        repository.toggleWishList("123")

        verify(dao).updateWishlistStatus("123", true)
    }

    @Test
    fun `toggleWishList updates wishlist status to false when current value is true`() = runTest {
        val entity = createEntity(id = "123", isInWishlist = true)

        whenever(dao.getAppDetails("123")).thenReturn(flowOf(entity))

        repository.toggleWishList("123")

        verify(dao).updateWishlistStatus("123", false)
    }

    @Test
    fun `toggleWishList does not update wishlist status when entity does not exist`() = runTest {
        whenever(dao.getAppDetails("123")).thenReturn(flowOf(null))

        repository.toggleWishList("123")

        verify(dao, never()).updateWishlistStatus(any(), any())
    }

    @Test
    fun `observeAppDetails returns mapped domain from dao flow`() = runTest {
        val entity = createEntity(id = "123")
        val expected = createDomain(id = "123")

        whenever(dao.getAppDetails("123")).thenReturn(flowOf(entity))
        whenever(entityMapper.toDomain(entity)).thenReturn(expected)

        val result = repository.observeAppDetails("123").first()

        assertEquals(expected, result)
    }

    @Test
    fun `observeAppDetails filters null values from dao flow`() = runTest {
        val entity = createEntity(id = "123")
        val expected = createDomain(id = "123")

        whenever(dao.getAppDetails("123")).thenReturn(flowOf(null, entity))
        whenever(entityMapper.toDomain(entity)).thenReturn(expected)

        val result = repository.observeAppDetails("123").toList()

        assertEquals(listOf(expected), result)
    }

    private fun createDto(
        id: String = "123",
        name: String = "VK",
        developer: String = "VK Dev",
        category: String = "Игры",
        ageRating: Int = 12,
        size: Double = 125.5,
        iconUrl: String = "icon_url",
        screenshotUrlList: List<String> = listOf("screen_1", "screen_2"),
        description: String = "description"
    ): AppDetailsDto {
        return AppDetailsDto(
            id = id,
            name = name,
            developer = developer,
            category = category,
            ageRating = ageRating,
            size = size,
            iconUrl = iconUrl,
            screenshotUrlList = screenshotUrlList,
            description = description
        )
    }

    private fun createDomain(
        id: String = "123",
        name: String = "VK",
        developer: String = "VK Dev",
        category: Category = Category.APP,
        ageRating: Int = 12,
        size: Float = 125.5f,
        iconUrl: String = "icon_url",
        screenshots: List<String>? = listOf("screen_1", "screen_2"),
        description: String = "description",
        isInWishlist: Boolean = false
    ): AppDetails {
        return AppDetails(
            id = id,
            name = name,
            developer = developer,
            category = category,
            ageRating = ageRating,
            size = size,
            iconUrl = iconUrl,
            screenshots = screenshots,
            description = description,
            isInWishlist = isInWishlist
        )
    }

    private fun createEntity(
        id: String = "123",
        name: String = "VK",
        developer: String = "VK Dev",
        category: Category = Category.APP,
        ageRating: Int = 12,
        size: Float = 125.5f,
        iconUrl: String = "icon_url",
        screenshots: List<String>? = null,
        description: String = "description",
        isInWishlist: Boolean = false
    ): AppDetailsEntity {
        return AppDetailsEntity(
            id = id,
            name = name,
            developer = developer,
            category = category,
            ageRating = ageRating,
            size = size,
            iconUrl = iconUrl,
            screenshots = null,
            description = description,
            isInWishlist = isInWishlist
        )
    }
}