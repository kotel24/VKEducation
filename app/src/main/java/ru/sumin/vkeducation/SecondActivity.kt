package ru.sumin.vkeducation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.sumin.vkeducation.screen.SecondScreen
import ru.sumin.vkeducation.ui.theme.VKEducationTheme
import ru.sumin.vkeducation.util.Contract

class SecondActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val receivedText = Contract.readText(intent = intent)
        setContent {
            VKEducationTheme {
                SecondScreen(receivedText)
            }
        }
    }
}