package ru.sumin.vkeducation.presentation

import ru.sumin.vkeducation.presentation.copy.App

data class AppListUiState (
    val apps: List<App> = emptyList()
)