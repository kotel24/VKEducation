package ru.sumin.vkeducation.domain.applist

import ru.sumin.vkeducation.domain.appdetails.Category

data class AppsList(
    val name: String,
    val developer: String,
    val category: Category,
    val ageRating: Int,
    val size: Float,
    val iconUrl: String,
    val screenshotUrlList: List<String>,
    val description: String,
)