package ru.sumin.vkeducation.domain.appdetails

import javax.inject.Inject

class GetAppDetailsUseCase @Inject constructor(
    private val appDetailsRepository: AppDetailsRepository
){
    suspend operator fun invoke(): AppDetails{
        val app: AppDetails = appDetailsRepository.get()

        if (app.category == Category.GAME) {
            throw IllegalStateException()
        }
        return app
    }
}