package ru.sumin.vkeducation.domain.appdetails

import ru.sumin.vkeducation.data.appdetails.AppDetailsRepositorImpl

class GetAppDetailsUseCase(
    private val appDetailsRepository: AppDetailsRepositorImpl
){
    suspend operator fun invoke(): AppDetails{
        val app: AppDetails = appDetailsRepository.get()

        if (app.category == Category.GAME) {
            throw IllegalStateException()
        }
        return app
    }
}