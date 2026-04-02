package ru.sumin.vkeducation.data.applist

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import ru.sumin.vkeducation.domain.applist.AppsList

class AppsListRepositoryImplTest {

    private lateinit var api: AppsListApi
    private lateinit var mapper: AppsListMapper
    private lateinit var repository: AppsListRepositoryImpl

    @Before
    fun setUp() {
        api = mock()
        mapper = mock()
        repository = AppsListRepositoryImpl(api, mapper)
    }

    @Test
    fun `get calls api getAppsList`() = runTest {
        whenever(api.getAppsList()).thenReturn(emptyList())

        repository.get()

        verify(api).getAppsList()
    }

    @Test
    fun `get maps every dto from api response to domain`() = runTest {
        val firstDto = createDto(id = "1", name = "VK")
        val secondDto = createDto(id = "2", name = "Telegram")

        whenever(api.getAppsList()).thenReturn(listOf(firstDto, secondDto))
        whenever(mapper.toDomain(firstDto)).thenReturn(createDomain(id = "1", name = "VK"))
        whenever(mapper.toDomain(secondDto)).thenReturn(createDomain(id = "2", name = "Telegram"))

        repository.get()

        verify(mapper).toDomain(firstDto)
        verify(mapper).toDomain(secondDto)
    }

    @Test
    fun `get calls mapper toDomain exactly for each dto from api response`() = runTest {
        val firstDto = createDto(id = "1")
        val secondDto = createDto(id = "2")

        whenever(api.getAppsList()).thenReturn(listOf(firstDto, secondDto))
        whenever(mapper.toDomain(any())).thenReturn(createDomain())

        repository.get()

        verify(mapper, times(2)).toDomain(any())
    }

    @Test
    fun `get returns mapped domain list`() = runTest {
        val firstDto = createDto(id = "1", name = "VK")
        val secondDto = createDto(id = "2", name = "Telegram")

        val firstDomain = createDomain(id = "1", name = "VK")
        val secondDomain = createDomain(id = "2", name = "Telegram")

        whenever(api.getAppsList()).thenReturn(listOf(firstDto, secondDto))
        whenever(mapper.toDomain(firstDto)).thenReturn(firstDomain)
        whenever(mapper.toDomain(secondDto)).thenReturn(secondDomain)

        val result = repository.get()

        assertEquals(listOf(firstDomain, secondDomain), result)
    }

    @Test
    fun `get returns empty list when api returns empty list`() = runTest {
        whenever(api.getAppsList()).thenReturn(emptyList())

        val result = repository.get()

        assertEquals(emptyList<AppsList>(), result)
    }

    @Test(expected = RuntimeException::class)
    fun `get throws exception when api throws exception`() = runTest {
        whenever(api.getAppsList()).thenThrow(RuntimeException())

        repository.get()
    }

    private fun createDto(
        id: String = "123",
        name: String = "VK",
        category: String = "Социальные сети",
        iconUrl: String = "icon_url",
        description: String = "description"
    ): AppsListDto {
        return AppsListDto(
            id = id,
            name = name,
            category = category,
            iconUrl = iconUrl,
            description = description
        )
    }

    private fun createDomain(
        id: String = "123",
        name: String = "VK",
        category: String = "Социальные сети",
        iconUrl: String = "icon_url",
        description: String = "description"
    ): AppsList {
        return AppsList(
            id = id,
            name = name,
            category = category,
            iconUrl = iconUrl,
            description = description
        )
    }
}