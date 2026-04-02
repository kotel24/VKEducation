package ru.sumin.vkeducation.domain.appdetails

import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ObserveAppDetailsUseCaseTest {

    private lateinit var repository: AppDetailsRepository
    private lateinit var useCase: ObserveAppDetailsUseCase

    @Before
    fun setUp() {
        repository = mock()
        useCase = ObserveAppDetailsUseCase(repository)
    }

    @Test
    fun `invoke calls repository observeAppDetails with same id`() = runTest {
        whenever(repository.observeAppDetails("123")).thenReturn(flowOf(createAppDetails()))

        useCase("123")

        verify(repository).observeAppDetails("123")
    }

    @Test
    fun `invoke returns same app details from repository flow`() = runTest {
        val expected = createAppDetails()

        whenever(repository.observeAppDetails("123")).thenReturn(flowOf(expected))

        val result = useCase("123").first()

        assertEquals(expected, result)
    }

    @Test
    fun `invoke returns all app details from repository flow`() = runTest {
        val first = createAppDetails(id = "1", name = "VK")
        val second = createAppDetails(id = "2", name = "Telegram")

        whenever(repository.observeAppDetails("123")).thenReturn(flowOf(first, second))

        val result = useCase("123").toList()

        assertEquals(listOf(first, second), result)
    }

    @Test
    fun `invoke returns empty list when repository returns empty flow`() = runTest {
        whenever(repository.observeAppDetails("123")).thenReturn(emptyFlow())

        val result = useCase("123").toList()

        assertEquals(emptyList<AppDetails>(), result)
    }

    private fun createAppDetails(
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
}