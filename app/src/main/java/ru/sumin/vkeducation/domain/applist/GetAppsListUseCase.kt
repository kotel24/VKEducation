package ru.sumin.vkeducation.domain.applist

class GetAppsListUseCase (
    private val repository: AppsListRepository
){
    suspend operator fun invoke(): List<AppsList> {
        val app: List<AppsList> = repository.get()
        return app
    }
}