package ru.sumin.vkeducation.domain.appdetails

interface AppDetailsRepository {
    suspend fun get(): AppDetails
}