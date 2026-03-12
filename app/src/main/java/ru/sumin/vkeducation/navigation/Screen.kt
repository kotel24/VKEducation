package ru.sumin.vkeducation.navigation

sealed class Screen(val route: String) {

    data object AppsList : Screen(APPS_LIST)

    data object AppDetails : Screen(APPS_DETAILS)

    companion object {
        private const val APPS_LIST = "apps_list"
        private const val APPS_DETAILS = "app_details"
    }
}