package ru.sumin.vkeducation.presentation.applist

import ru.sumin.vkeducation.domain.appdetails.AppDetails

data class AppListUiState (
    val appDetails: List<AppDetails> = emptyList()
)