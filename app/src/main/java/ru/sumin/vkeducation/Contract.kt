package ru.sumin.vkeducation

import android.content.Context
import android.content.Intent

object Contract {
    private const val EXTRA_TEXT = "extra_text"

    fun createIntent(context: Context, text: String): Intent {
        return Intent(context, SecondActivity::class.java)
            .putExtra(EXTRA_TEXT, text)
    }

    fun readText(intent: Intent): String {
        return intent.getStringExtra(EXTRA_TEXT).orEmpty()
    }
}