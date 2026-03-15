package ru.sumin.vkeducation.presentation.applist

sealed interface AppsListEvent {
    data class ShowSnackbar(val message: String) : AppsListEvent
}