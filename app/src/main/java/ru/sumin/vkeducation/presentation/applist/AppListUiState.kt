package ru.sumin.vkeducation.presentation.applist

import ru.sumin.vkeducation.domain.applist.AppsList

sealed interface AppListUiState {
    data object Error: AppListUiState
    data object Loading: AppListUiState
    data class Content(
        val appsList: List<AppsList> = emptyList(),
    ): AppListUiState
}