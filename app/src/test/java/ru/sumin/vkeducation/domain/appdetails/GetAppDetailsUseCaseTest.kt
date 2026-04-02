package ru.sumin.vkeducation.domain.appdetails

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetAppDetailsUseCaseTest {

    private lateinit var repository: AppDetailsRepository
    private lateinit var useCase: GetAppDetailsUseCase

    @Before
    fun setUp() {
        repository = mock()
        useCase = GetAppDetailsUseCase(repository)
    }

    @Test
    fun `invoke calls repository getAppDetails with same id`() = runTest {
        whenever(repository.getAppDetails("123")).thenReturn(createAppDetails())

        useCase("123")

        verify(repository).getAppDetails("123")
    }

    @Test
    fun `invoke returns same app details as repository`() = runTest {
        val expected = createAppDetails()

        whenever(repository.getAppDetails("123")).thenReturn(expected)

        val result = useCase("123")

        assertEquals(expected, result)
    }

    @Test(expected = RuntimeException::class)
    fun `invoke throws exception when repository throws exception`() = runTest {
        whenever(repository.getAppDetails("123")).thenThrow(RuntimeException())

        useCase("123")
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