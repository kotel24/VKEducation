package ru.sumin.vkeducation.domain.applist

import javax.inject.Inject

class GetAppsListUseCase @Inject constructor(
    private val repository: AppsListRepository
){
    suspend operator fun invoke(): List<AppsList> {
        return repository.get()
    }
}