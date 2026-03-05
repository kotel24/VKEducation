package ru.sumin.vkeducation.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import ru.sumin.vkeducation.SecondActivity

object Contract {
    private const val EXTRA_TEXT = "extra_text"

    fun createIntent(context: Context, text: String): Intent {
        return Intent(context, SecondActivity::class.java)
            .putExtra(EXTRA_TEXT, text)
    }

    fun readText(intent: Intent): String {
        return intent.getStringExtra(EXTRA_TEXT).orEmpty()
    }

    fun uriIntent(uri: Uri): Intent{
        return Intent(Intent.ACTION_DIAL).apply { data = uri }
    }

    fun  chooserIntent(text: String): Intent{
        return Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        }
    }
}