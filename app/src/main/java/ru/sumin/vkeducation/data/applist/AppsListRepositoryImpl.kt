package ru.sumin.vkeducation.data.applist

import ru.sumin.vkeducation.domain.applist.AppsList
import ru.sumin.vkeducation.domain.applist.AppsListRepository

class AppsListRepositoryImpl: AppsListRepository {
    private val appsListApi = AppsListApi()
    private val mapper = AppsListMapper()
    override suspend fun get(): List<AppsList> {
        val dto = appsListApi.getAppsList()
        val domains = dto.map { dto ->
            mapper.toDomain(dto)
        }
        return domains
    }

}