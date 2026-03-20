package ru.sumin.vkeducation.data.appdetails

import ru.sumin.vkeducation.domain.appdetails.AppDetails
import ru.sumin.vkeducation.domain.appdetails.Category
import javax.inject.Inject

class AppDetailsMapper @Inject constructor(){
    fun toDomain(dto: AppDetailsDto): AppDetails = AppDetails(
        id = dto.id,
        name = dto.name,
        developer = dto.developer,
        category = mapCategory(dto.category),
        ageRating = dto.ageRating,
        size = dto.size.toFloat(),
        iconUrl = dto.iconUrl,
        screenshots = dto.screenshotUrlList,
        description = dto.description,
    )

    private fun mapCategory(category: String): Category = when (category) {
        "Приложения" -> Category.APP
        "Игры" -> Category.GAME
        "Производительность" -> Category.PRODUCTIVITY
        "Социальные сети" -> Category.SOCIAL
        "Образование" -> Category.EDUCATION
        "Развлечения" -> Category.ENTERTAINMENT
        "Музыка" -> Category.MUSIC
        "Видео" -> Category.VIDEO
        "Фотография" -> Category.PHOTOGRAPHY
        "Здоровье" -> Category.HEALTH
        "Спорт" -> Category.SPORTS
        "Новости" -> Category.NEWS
        "Книги" -> Category.BOOKS
        "Бизнес" -> Category.BUSINESS
        "Финансы" -> Category.FINANCE
        "Путешествия" -> Category.TRAVEL
        "Карты" -> Category.MAPS
        "Еда" -> Category.FOOD
        "Покупки" -> Category.SHOPPING
        "Утилиты" -> Category.UTILITIES
        else -> Category.APP
    }
}