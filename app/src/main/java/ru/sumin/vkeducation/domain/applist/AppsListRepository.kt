package ru.sumin.vkeducation.domain.applist

interface AppsListRepository {
    suspend fun get(): List<AppsList>
}