package ru.sumin.vkeducation.data.appdetails

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import ru.sumin.vkeducation.domain.appdetails.Category

class AppDetailsMapperTest {
    private lateinit var mapper: AppDetailsMapper

    @Before
    fun setUp(){
        mapper = AppDetailsMapper()
    }

    @Test
    fun `toDomain returns domain model with same id as dto`(){
        val result = mapper.toDomain(createDto(id = "123"))

        assertEquals("123", result.id)
    }

    @Test
    fun `toDomain returns domain model with same ageRating as dto`() {
        val result = mapper.toDomain(createDto(ageRating = 12))

        assertEquals(12, result.ageRating)
    }

    @Test
    fun `toDomain returns domain model with float size converted from dto size`() {
        val result = mapper.toDomain(createDto(size = 125.5))

        assertEquals(125.5f, result.size)
    }

    @Test
    fun `toDomain returns domain model with same screenshots as dto`() {
        val screenshots = listOf("screen_1", "screen_2")

        val result = mapper.toDomain(createDto(screenshotUrlList = screenshots))

        assertEquals(screenshots, result.screenshots)
    }

    @Test
    fun `toDomain returns GAME category when dto category is Игры`() {
        val result = mapper.toDomain(createDto(category = "Игры"))

        assertEquals(Category.GAME, result.category)
    }

    @Test
    fun `toDomain returns APP category when dto category is unknown`() {
        val result = mapper.toDomain(createDto(category = "Неизвестная категория"))

        assertEquals(Category.APP, result.category)
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
}