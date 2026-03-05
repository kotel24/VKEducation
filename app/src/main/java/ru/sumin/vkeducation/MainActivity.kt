package ru.sumin.vkeducation

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.sumin.vkeducation.screen.MainScreen
import ru.sumin.vkeducation.ui.theme.VKEducationTheme
import ru.sumin.vkeducation.util.Contract
import ru.sumin.vkeducation.util.sanitizePhone
import ru.sumin.vkeducation.util.validatePhone

class MainActivity : ComponentActivity() {

    private var text by mutableStateOf("")
    private var error by mutableStateOf<String?>(null)

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKEducationTheme {
                MainScreen(
                    text = text,
                    error = error,
                    onTextChanged = { input ->
                        val sanitized = sanitizePhone(input)
                        text = sanitized
                        error = validatePhone(sanitized)
                    },
                    onOpenSecond = {
                        val intent = Contract.createIntent(this, it)
                        startActivity(intent)
                    },
                    onDial = {
                        if (error == null) {
                            val uri = Uri.fromParts("tel", text, null)
                            startActivity(Intent(Intent.ACTION_DIAL).apply { data = uri })
                        }
                    }
                )
            }
        }
    }
}