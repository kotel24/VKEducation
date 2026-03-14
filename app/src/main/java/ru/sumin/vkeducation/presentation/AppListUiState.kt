package ru.sumin.vkeducation.presentation

import ru.sumin.vkeducation.presentation.appdetails.AppDetails

data class AppListUiState (
    val appDetails: List<AppDetails> = emptyList()
)