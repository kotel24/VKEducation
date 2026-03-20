package ru.sumin.vkeducation.data.applist

import retrofit2.http.GET

interface AppsListApi{
    @GET("catalog")
    suspend fun getAppsList(): List<AppsListDto>
}