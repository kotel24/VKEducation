package ru.sumin.vkeducation.data.appdetails

import ru.sumin.vkeducation.domain.appdetails.AppDetails
import ru.sumin.vkeducation.domain.appdetails.AppDetailsRepository
import javax.inject.Inject

class AppDetailsRepositoryImpl @Inject constructor(
    private val appDetailsApi: AppDetailsApi,
    private val mapper: AppDetailsMapper
) : AppDetailsRepository {


    override suspend fun getAppDetails(id: String): AppDetails {
        val dto = appDetailsApi.getAppDetails(id)
        val domain = mapper.toDomain(dto)
        return domain
    }
}