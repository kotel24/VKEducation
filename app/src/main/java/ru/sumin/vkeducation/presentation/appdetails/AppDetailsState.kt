package ru.sumin.vkeducation.presentation.appdetails

sealed interface AppDetailsState {
    data object Error: AppDetailsState
    data object Loading: AppDetailsState
    data class Content(
        val appDetails: AppDetails,
        val descriptionCollapsed: Boolean
    ): AppDetailsState
}