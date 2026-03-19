package ru.sumin.vkeducation.data.applist

import ru.sumin.vkeducation.domain.applist.AppsList
import javax.inject.Inject

class AppsListMapper @Inject constructor(){
    fun toDomain(dto: AppsListDto): AppsList = AppsList(
        name = dto.name,
        developer = dto.developer,
        category = dto.category,
        ageRating = dto.ageRating,
        size = dto.size,
        iconUrl = dto.iconUrl,
        screenshotUrlList = dto.screenshotUrlList,
        description = dto.description
    )
}