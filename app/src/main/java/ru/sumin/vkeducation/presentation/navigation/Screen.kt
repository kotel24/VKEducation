package ru.sumin.vkeducation.presentation.navigation

sealed class Screen(val route: String) {
    data object AppsList : Screen("apps_list")
    data object AppDetails : Screen("app_details/{id}") {
        fun createRoute(id: String): String = "app_details/$id"
    }
}