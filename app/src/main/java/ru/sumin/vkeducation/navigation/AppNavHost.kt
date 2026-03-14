package ru.sumin.vkeducation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.sumin.vkeducation.presentation.AppsListScreen
import ru.sumin.vkeducation.presentation.appdetails.AppDetailsScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.AppsList.route,
    ) {
        composable(Screen.AppsList.route) {
            AppsListScreen(
                onAppClick = {
                    navController.navigate(Screen.AppDetails.route)
                }
            )
        }

        composable(Screen.AppDetails.route) {
            AppDetailsScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}