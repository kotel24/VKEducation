package ru.sumin.vkeducation.presentation.appdetails

sealed interface AppDetailsEvent{
    data object UnderDevelopment : AppDetailsEvent
}