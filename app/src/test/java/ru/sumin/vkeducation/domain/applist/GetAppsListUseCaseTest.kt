package ru.sumin.vkeducation.domain.applist

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetAppsListUseCaseTest {

    private lateinit var repository: AppsListRepository
    private lateinit var useCase: GetAppsListUseCase

    @Before
    fun setUp() {
        repository = mock()
        useCase = GetAppsListUseCase(repository)
    }

    @Test
    fun `invoke calls repository get`() = runTest {
        whenever(repository.get()).thenReturn(emptyList())

        useCase()

        verify(repository).get()
    }

    @Test
    fun `invoke calls repository get exactly once`() = runTest {
        whenever(repository.get()).thenReturn(emptyList())

        useCase()

        verify(repository, times(1)).get()
    }

    @Test
    fun `invoke returns same app list as repository`() = runTest {
        val expected = listOf(
            AppsList(
                id = "1",
                name = "VK",
                category = "Социальные сети",
                iconUrl = "icon_url",
                description = "description"
            )
        )
        whenever(repository.get()).thenReturn(expected)

        val result = useCase()

        assertEquals(expected, result)
    }

    @Test
    fun `invoke returns empty list when repository returns empty list`() = runTest {
        whenever(repository.get()).thenReturn(emptyList())

        val result = useCase()

        assertEquals(emptyList<AppsList>(), result)
    }

    @Test(expected = RuntimeException::class)
    fun `invoke throws exception when repository throws exception`() = runTest {
        whenever(repository.get()).thenThrow(RuntimeException())

        useCase()
    }
}