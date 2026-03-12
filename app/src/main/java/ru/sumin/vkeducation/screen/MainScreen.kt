package ru.sumin.vkeducation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ru.sumin.vkeducation.util.sanitizePhone

@Composable
fun MainScreen(
    text: String,
    error: String?,
    onTextChanged: (String) -> Unit,
    onOpenSecond: (String) -> Unit,
    onDial: (String) -> Unit,
    onShare: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { onTextChanged(sanitizePhone(it)) },
            label = { Text("Номер телефона") },
            isError = error != null,
            supportingText = { Text(error ?: "Пример: +79991234567") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {onOpenSecond(text)},
        ) {
            Text("Открыть вторую Activity")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {onDial(text)},
            enabled = error == null && text.isNotBlank()
        ) {
            Text("Открыть приложение для звонков")
        }

        Button(
            onClick = onShare,
            enabled = text.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Поделиться текстом")
        }
    }
}