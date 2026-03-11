package ru.sumin.vkeducation.presentation

sealed interface AppsListEvent {
    data class ShowSnackbar(val message: String) : AppsListEvent
}