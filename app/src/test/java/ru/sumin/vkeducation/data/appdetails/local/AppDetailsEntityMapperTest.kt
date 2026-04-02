package ru.sumin.vkeducation.data.appdetails.local

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import ru.sumin.vkeducation.domain.appdetails.AppDetails
import ru.sumin.vkeducation.domain.appdetails.Category

class AppDetailsEntityMapperTest {

    private lateinit var mapper: AppDetailsEntityMapper

    @Before
    fun setUp() {
        mapper = AppDetailsEntityMapper()
    }

    @Test
    fun `toEntity returns entity with same id as domain`() {
        val result = mapper.toEntity(createDomain(id = "123"))

        assertEquals("123", result.id)
    }

    @Test
    fun `toEntity returns entity with same name as domain`() {
        val result = mapper.toEntity(createDomain(name = "VK"))

        assertEquals("VK", result.name)
    }

    @Test
    fun `toEntity returns entity with same category as domain`() {
        val result = mapper.toEntity(createDomain(category = Category.GAME))

        assertEquals(Category.GAME, result.category)
    }

    @Test
    fun `toEntity returns entity with null screenshots`() {
        val result = mapper.toEntity(
            createDomain(screenshots = listOf("screen_1", "screen_2"))
        )

        assertNull(result.screenshots)
    }

    @Test
    fun `toEntity returns entity with same wishlist status as domain`() {
        val result = mapper.toEntity(createDomain(isInWishlist = true))

        assertEquals(true, result.isInWishlist)
    }

    @Test
    fun `toDomain returns domain model with same id as entity`() {
        val result = mapper.toDomain(createEntity(id = "123"))

        assertEquals("123", result.id)
    }

    @Test
    fun `toDomain returns domain model with same name as entity`() {
        val result = mapper.toDomain(createEntity(name = "VK"))

        assertEquals("VK", result.name)
    }

    @Test
    fun `toDomain returns domain model with same category as entity`() {
        val result = mapper.toDomain(createEntity(category = Category.GAME))

        assertEquals(Category.GAME, result.category)
    }

    @Test
    fun `toDomain returns domain model with null screenshots`() {
        val result = mapper.toDomain(
            createEntity(screenshots = listOf("screen_1", "screen_2"))
        )

        assertNull(result.screenshots)
    }

    @Test
    fun `toDomain returns domain model with same wishlist status as entity`() {
        val result = mapper.toDomain(createEntity(isInWishlist = true))

        assertEquals(true, result.isInWishlist)
    }

    private fun createDomain(
        id: String = "123",
        name: String = "VK",
        developer: String = "VK Dev",
        category: Category = Category.APP,
        ageRating: Int = 12,
        size: Float = 120.5f,
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
        size: Float = 120.5f,
        iconUrl: String = "icon_url",
        screenshots: List<String>? = listOf("screen_1", "screen_2"),
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
            screenshots = screenshots?.first(),
            description = description,
            isInWishlist = isInWishlist
        )
    }
}