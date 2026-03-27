package ru.sumin.vkeducation.domain.appdetails

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveAppDetailsUseCase @Inject constructor(
    private val appDetailsRepository: AppDetailsRepository
) {
    operator fun invoke(id: String): Flow<AppDetails> {
        return appDetailsRepository.observeAppDetails(id)
    }
}