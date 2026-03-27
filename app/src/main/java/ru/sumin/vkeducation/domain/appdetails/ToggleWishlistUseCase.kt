package ru.sumin.vkeducation.domain.appdetails

import javax.inject.Inject

class ToggleWishlistUseCase @Inject constructor(
    private val appDetailsRepository: AppDetailsRepository
) {
    suspend operator fun invoke(id: String) {
        appDetailsRepository.toggleWishList(id)
    }
}