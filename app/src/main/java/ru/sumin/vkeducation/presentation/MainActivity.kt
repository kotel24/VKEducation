package ru.sumin.vkeducation.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.sumin.vkeducation.presentation.navigation.AppNavHost
import ru.sumin.vkeducation.presentation.ui.theme.VKEducationTheme
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKEducationTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}
