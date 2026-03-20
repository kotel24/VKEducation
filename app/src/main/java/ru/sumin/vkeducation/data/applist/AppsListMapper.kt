package ru.sumin.vkeducation.data.applist

import ru.sumin.vkeducation.domain.applist.AppsList
import javax.inject.Inject

class AppsListMapper @Inject constructor(){
    fun toDomain(dto: AppsListDto): AppsList = AppsList(
        name = dto.name,
        category = dto.category,
        iconUrl = dto.iconUrl,
        description = dto.description,
        id = dto.id,
    )
}