package ru.sumin.vkeducation.domain.appdetails

import kotlinx.coroutines.flow.Flow

interface AppDetailsRepository {
    suspend fun getAppDetails(id: String): AppDetails

    suspend fun toggleWishList(id: String)

    fun observeAppDetails(id: String): Flow<AppDetails>
}
