package ru.sumin.vkeducation.data.applist

import ru.sumin.vkeducation.domain.applist.AppsList
import ru.sumin.vkeducation.domain.applist.AppsListRepository
import javax.inject.Inject

class AppsListRepositoryImpl @Inject constructor(
    private val appsListApi: AppsListApi,
    private val mapper: AppsListMapper
) : AppsListRepository {
    override suspend fun get(): List<AppsList> {
        val dto = appsListApi.getAppsList()
        val domains = dto.map { dto ->
            mapper.toDomain(dto)
        }
        return domains
    }

}