package ru.sumin.vkeducation.data.appdetails

import ru.sumin.vkeducation.domain.appdetails.AppDetails
import ru.sumin.vkeducation.domain.appdetails.AppDetailsRepository

class AppDetailsRepositorImpl : AppDetailsRepository {
    private val appApi = AppApi()
    private val mapper = AppDetailsMapper()

    override suspend fun get(): AppDetails {
        val dto = appApi.get()
        val domain = mapper.toDomain(dto)
        return domain
    }
}