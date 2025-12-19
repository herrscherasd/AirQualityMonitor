package com.example.smartairqualitymonitor.presentation.utils

import androidx.compose.ui.graphics.Color
import com.example.smartairqualitymonitor.domain.model.AirQuality
import com.example.smartairqualitymonitor.domain.model.QualityLevel

fun getStatusColor(status: QualityLevel): Color {
    return when (status) {
        QualityLevel.GOOD -> Color(0xFF4CAF50)
        QualityLevel.MODERATE -> Color(0xFFFFC107)
        QualityLevel.POOR -> Color(0xFFF44336)
        QualityLevel.UNKNOWN -> Color(0xFF9E9E9E)
    }
}

fun getStatusText(status: QualityLevel): String {
    return when (status) {
        QualityLevel.GOOD -> "Хорошее"
        QualityLevel.MODERATE -> "Умеренное"
        QualityLevel.POOR -> "Плохое"
        QualityLevel.UNKNOWN -> "Неизвестно"
    }
}

fun getDescription(status: QualityLevel): String {
    return when (status) {
        QualityLevel.GOOD -> "Воздух чистый, можно проветривать"
        QualityLevel.MODERATE -> "Воздух удовлетворительный"
        QualityLevel.POOR -> "Рекомендуется закрыть окна"
        QualityLevel.UNKNOWN -> "Не удалось определить качество воздуха"
    }
}

fun AirQuality.getColor(): Color = getStatusColor(this.getQualityLevel())
fun AirQuality.getStatusText(): String = getStatusText(this.getQualityLevel())
fun AirQuality.getDescription(): String = getDescription(this.getQualityLevel())

fun getStatusFromPpm(ppm: Int): QualityLevel {
    return when {
        ppm < 300 -> QualityLevel.GOOD
        ppm < 600 -> QualityLevel.MODERATE
        else -> QualityLevel.POOR
    }
}