package ru.sumin.vkeducation.util

fun sanitizePhone(input: String): String = buildString {
    input.forEachIndexed { index, ch ->
        when {
            ch.isDigit() -> append(ch)
            ch == '+' && index == 0 -> append(ch)
        }
    }
}

fun validatePhone(phone: String): String? {
    val digitsCount = phone.count { it.isDigit() }

    return when {
        phone.isBlank() -> "Введите номер телефона"
        digitsCount !in 10..15 -> "Номер должен содержать 10–15 цифр"
        else -> null
    }
}