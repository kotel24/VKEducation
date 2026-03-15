package ru.sumin.vkeducation.presentation.applist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import ru.sumin.vkeducation.domain.appdetails.AppDetails
import ru.sumin.vkeducation.domain.appdetails.Category

class AppListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        AppListUiState(appDetails = getAppsList())
    )

    val uiState = _uiState.asStateFlow()

    private val _events = Channel<AppsListEvent>(Channel.Factory.BUFFERED)

    val events = _events.receiveAsFlow()

    fun onLogoClick() {
        _events.trySend(
            AppsListEvent.ShowSnackbar("Логотип RuStore нажат")
        )
    }

    private fun getAppsList(): List<AppDetails> = listOf(
        AppDetails(
            name = "СберБанк Онлайн — с Салютом",
            developer = "Больше чем банк",
            category = Category.APP,
            ageRating = 0,
            size = 85.0f,
            iconUrl = "https://static.rustore.ru/imgproxy/lQKIdJKRbtJBX0dxbZueqU-a5TEP_-_yKjFjWljOsaE/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/462271/content/ICON/f1b3c68a-b734-48ce-b62f-490208d3fa0e.png@webp",
            screenshotUrlList = emptyList(),
            description = "Финансы",
        ),
        AppDetails(
            name = "Яндекс.Браузер — с Алисой",
            developer = "Быстрый и безопасный браузер",
            category = Category.APP,
            ageRating = 0,
            size = 120.0f,
            iconUrl = "https://static.rustore.ru/imgproxy/rGr87NnjSOsiX-imht9uyNnHK-YDQJNvIlY2rIb4gsA/preset:web_app_icon_62/plain/https://static.rustore.ru/2025/10/25/1e/apk/579007/content/ICON/939321c0-03f7-484d-9043-c0fb12736ef1.png@webp",
            screenshotUrlList = emptyList(),
            description = "Инструменты",
        ),
        AppDetails(
            name = "Почта Mail.ru",
            developer = "Почтовый клиент для любых ящиков",
            category = Category.APP,
            ageRating = 0,
            size = 95.0f,
            iconUrl = "https://static.rustore.ru/imgproxy/ZXaYO3sOPXl2OZA9s-f4F7vXfXN1KGDEy-4DcGmXuRQ/preset:web_app_icon_62/plain/https://static.rustore.ru/2025/12/18/49/apk/332223/content/ICON/79bd5fd2-13fb-4218-874f-7d3d651d344f.png@webp",
            screenshotUrlList = emptyList(),
            description = "Инструменты",
        ),
        AppDetails(
            name = "Яндекс Навигатор",
            developer = "Парковки и заправки — по пути",
            category = Category.APP,
            ageRating = 0,
            size = 110.0f,
            iconUrl = "https://static.rustore.ru/imgproxy/P0Em8fwcZIhhOo3WQzCBmTlyT2Q9Xw1FJtx0nEwuEoU/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/595135/content/ICON/32cb5e63-9c59-4280-9a6a-c808113be88f.png@webp",
            screenshotUrlList = emptyList(),
            description = "Транспорт",
        ),
        AppDetails(
            name = "Мой МТС",
            developer = "Мой МТС — центр экосистемы МТС",
            category = Category.APP,
            ageRating = 0,
            size = 77.0f,
            iconUrl = "https://static.rustore.ru/imgproxy/ycc_vNBYtD70IPhATGiRARVzqBTQHzHlJK5fgR6DCPQ/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/336831/content/ICON/ea6c9e63-bd7f-486f-ac3f-3e9069ecf018.png@webp",
            screenshotUrlList = emptyList(),
            description = "Инструменты",
        ),
        AppDetails(
            name = "Яндекс — с Алисой",
            developer = "Яндекс — поиск всегда под рукой",
            category = Category.APP,
            ageRating = 0,
            size = 92.0f,
            iconUrl = "https://static.rustore.ru/imgproxy/1A_F3rBwWHQ5Z_aZcwxyI24YceoUAqpCKqSn5gWtlqo/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/313257919/content/ICON/843c5040-0e09-41bb-958c-b7bacc912c2b.png@webp",
            screenshotUrlList = emptyList(),
            description = "Инструменты",
        ),
    )
}