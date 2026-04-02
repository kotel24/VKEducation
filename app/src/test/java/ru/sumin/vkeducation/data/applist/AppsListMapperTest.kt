package ru.sumin.vkeducation.data.applist

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AppsListMapperTest {

    private lateinit var mapper: AppsListMapper

    @Before
    fun setUp() {
        mapper = AppsListMapper()
    }

    @Test
    fun `toDomain returns domain model with same id as dto`() {
        val result = mapper.toDomain(createDto(id = "123"))

        assertEquals("123", result.id)
    }

    @Test
    fun `toDomain returns domain model with same name as dto`() {
        val result = mapper.toDomain(createDto(name = "VK"))

        assertEquals("VK", result.name)
    }

    @Test
    fun `toDomain returns domain model with same category as dto`() {
        val result = mapper.toDomain(createDto(category = "Социальные сети"))

        assertEquals("Социальные сети", result.category)
    }

    @Test
    fun `toDomain returns domain model with same iconUrl as dto`() {
        val result = mapper.toDomain(createDto(iconUrl = "icon_url"))

        assertEquals("icon_url", result.iconUrl)
    }

    @Test
    fun `toDomain returns domain model with same description as dto`() {
        val result = mapper.toDomain(createDto(description = "description"))

        assertEquals("description", result.description)
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
}