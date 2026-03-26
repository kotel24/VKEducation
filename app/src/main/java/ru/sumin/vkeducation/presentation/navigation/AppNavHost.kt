package ru.sumin.vkeducation.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.sumin.vkeducation.presentation.appdetails.AppDetailsScreen
import ru.sumin.vkeducation.presentation.applist.AppsListScreen

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
                onAppClick = { apps ->
                    navController.navigate(Screen.AppDetails.createRoute(apps.id))
                }
            )
        }

        composable(
            route = Screen.AppDetails.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) {
            AppDetailsScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}