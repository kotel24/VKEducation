package ru.sumin.vkeducation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.sumin.vkeducation.screen.MainScreen
import ru.sumin.vkeducation.ui.theme.VKEducationTheme

class MainActivity : ComponentActivity() {

    private var text by mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKEducationTheme {
                MainScreen(
                    text = text,
                    onTextChanged = {text = it},
                    onOpenSecond = {
                        val intent = Contract.createIntent(this, it)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}