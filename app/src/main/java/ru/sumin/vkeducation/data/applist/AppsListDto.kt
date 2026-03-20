package ru.sumin.vkeducation.data.applist

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AppsListDto (
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("iconUrl")
    val iconUrl: String,
    )