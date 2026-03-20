package ru.sumin.vkeducation.domain.appdetails

interface AppDetailsRepository {
    suspend fun getAppDetails(id: String): AppDetails
}
