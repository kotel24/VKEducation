package ru.sumin.vkeducation.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ru.sumin.vkeducation.R


@Composable
fun AppsListScreen(
    modifier: Modifier = Modifier,
    onAppClick: (App) -> Unit = {},
) {
    val apps = remember { getAppsList() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0079FF))
    ) {
        Spacer(
            modifier = Modifier.windowInsetsTopHeight(WindowInsets.statusBars)
        )

        AppsListTopBar()

        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            color = Color.White,
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 10.dp),
            ) {
                items(
                    items = apps,
                    key = { it.name },
                ) { app ->
                    AppRow(
                        app = app,
                        onClick = { onAppClick(app) },
                    )
                }
            }
        }
    }
}

@Composable
private fun AppsListTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.rustore_logo),
                contentDescription = "RuStore logo",
                modifier = Modifier.size(44.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "RuStore",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Surface(
            modifier = Modifier.size(34.dp),
            shape = RoundedCornerShape(8.dp),
            color = Color.White.copy(alpha = 0.16f)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Outlined.Apps,
                    contentDescription = "Apps menu",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
private fun AppRow(
    app: App,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = app.iconUrl,
            contentDescription = app.name,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = app.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF151515),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = app.developer,
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF2D2D2D),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = app.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF8E8E93),
                maxLines = 1,
            )
        }
    }
}


private fun getAppsList(): List<App> = listOf(
    App(
        name = "СберБанк Онлайн — с Салютом",
        developer = "Больше чем банк",
        category = Category.APP,
        ageRating = 0,
        size = 85.0f,
        iconUrl = "https://static.rustore.ru/imgproxy/lQKIdJKRbtJBX0dxbZueqU-a5TEP_-_yKjFjWljOsaE/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/462271/content/ICON/f1b3c68a-b734-48ce-b62f-490208d3fa0e.png@webp",
        screenshotUrlList = emptyList(),
        description = "Финансы",
    ),
    App(
        name = "Яндекс.Браузер — с Алисой",
        developer = "Быстрый и безопасный браузер",
        category = Category.APP,
        ageRating = 0,
        size = 120.0f,
        iconUrl = "https://static.rustore.ru/imgproxy/rGr87NnjSOsiX-imht9uyNnHK-YDQJNvIlY2rIb4gsA/preset:web_app_icon_62/plain/https://static.rustore.ru/2025/10/25/1e/apk/579007/content/ICON/939321c0-03f7-484d-9043-c0fb12736ef1.png@webp",
        screenshotUrlList = emptyList(),
        description = "Инструменты",
    ),
    App(
        name = "Почта Mail.ru",
        developer = "Почтовый клиент для любых ящиков",
        category = Category.APP,
        ageRating = 0,
        size = 95.0f,
        iconUrl = "https://static.rustore.ru/imgproxy/ZXaYO3sOPXl2OZA9s-f4F7vXfXN1KGDEy-4DcGmXuRQ/preset:web_app_icon_62/plain/https://static.rustore.ru/2025/12/18/49/apk/332223/content/ICON/79bd5fd2-13fb-4218-874f-7d3d651d344f.png@webp",
        screenshotUrlList = emptyList(),
        description = "Инструменты",
    ),
    App(
        name = "Яндекс Навигатор",
        developer = "Парковки и заправки — по пути",
        category = Category.APP,
        ageRating = 0,
        size = 110.0f,
        iconUrl = "https://static.rustore.ru/imgproxy/P0Em8fwcZIhhOo3WQzCBmTlyT2Q9Xw1FJtx0nEwuEoU/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/595135/content/ICON/32cb5e63-9c59-4280-9a6a-c808113be88f.png@webp",
        screenshotUrlList = emptyList(),
        description = "Транспорт",
    ),
    App(
        name = "Мой МТС",
        developer = "Мой МТС — центр экосистемы МТС",
        category = Category.APP,
        ageRating = 0,
        size = 77.0f,
        iconUrl = "https://static.rustore.ru/imgproxy/ycc_vNBYtD70IPhATGiRARVzqBTQHzHlJK5fgR6DCPQ/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/336831/content/ICON/ea6c9e63-bd7f-486f-ac3f-3e9069ecf018.png@webp",
        screenshotUrlList = emptyList(),
        description = "Инструменты",
    ),
    App(
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