package ru.sumin.vkeducation.data.appdetails

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
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

    override suspend fun toggleWishList(id: String) {
        val currentEntity = dao.getAppDetails(id).first()
        currentEntity?.let{
            dao.updateWishlistStatus(id, !it.isInWishlist)
        }
    }

    override fun observeAppDetails(id: String): Flow<AppDetails> {
        return dao.getAppDetails(id)
            .filterNotNull()
            .map { entityMapper.toDomain(it) }
    }
}