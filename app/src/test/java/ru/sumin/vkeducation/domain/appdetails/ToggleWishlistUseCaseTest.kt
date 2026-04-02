package ru.sumin.vkeducation.domain.appdetails

import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ToggleWishlistUseCaseTest {

    private lateinit var repository: AppDetailsRepository
    private lateinit var useCase: ToggleWishlistUseCase

    @Before
    fun setUp() {
        repository = mock()
        useCase = ToggleWishlistUseCase(repository)
    }

    @Test
    fun `invoke calls repository toggleWishList with same id`() = runTest {
        useCase("123")

        verify(repository).toggleWishList("123")
    }

    @Test
    fun `invoke calls repository toggleWishList exactly once`() = runTest {
        useCase("123")

        verify(repository, times(1)).toggleWishList("123")
    }

    @Test
    fun `invoke calls repository toggleWishList for different id`() = runTest {
        useCase("app_42")

        verify(repository).toggleWishList("app_42")
    }

    @Test(expected = RuntimeException::class)
    fun `invoke throws exception when repository throws exception`() = runTest {
        whenever(repository.toggleWishList("123")).thenThrow(RuntimeException())

        useCase("123")
    }
}