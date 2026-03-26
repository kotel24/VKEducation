package ru.sumin.vkeducation.data.appdetails

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import ru.sumin.vkeducation.data.appdetails.local.AppDetailsDao
import ru.sumin.vkeducation.data.appdetails.local.AppDetailsEntityMapper
import ru.sumin.vkeducation.domain.appdetails.AppDetails
import ru.sumin.vkeducation.domain.appdetails.AppDetailsRepository
import javax.inject.Inject

class AppDetailsRepositoryImpl @Inject constructor(
    private val appApi: AppDetailsApi,
    private val dao: AppDetailsDao,
    private val mapper: AppDetailsMapper,
    private val entityMapper: AppDetailsEntityMapper,
) : AppDetailsRepository {
    override suspend fun getAppDetails(id: String): AppDetails {
        val entity = dao.getAppDetails(id).first()
        return if(entity != null) {
            entityMapper.toDomain(entity)
        } else {
            val dto = appApi.getAppDetails(id)
            val domain = mapper.toDomain(dto)
            val entity = entityMapper.toEntity(domain)
            withContext(Dispatchers.IO){
                dao.insertAppDetails(entity)
            }
            domain
        }
    }
}