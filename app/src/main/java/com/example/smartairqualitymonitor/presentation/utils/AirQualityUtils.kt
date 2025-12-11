package com.example.smartairqualitymonitor.presentation.utils

import androidx.compose.ui.graphics.Color
import com.example.smartairqualitymonitor.domain.model.AirQualityStatus

fun getStatusColor(status: AirQualityStatus): Color {
    return when (status) {
        AirQualityStatus.GOOD -> Color(0xFF4CAF50)
        AirQualityStatus.MODERATE -> Color(0xFFFF9800)
        AirQualityStatus.POOR -> Color(0xFFF44336)
    }
}

fun getStatusText(status: AirQualityStatus): String {
    return when (status) {
        AirQualityStatus.GOOD -> "Хорошее"
        AirQualityStatus.MODERATE -> "Умеренное"
        AirQualityStatus.POOR -> "Плохое"
    }
}

fun getDescription(status: AirQualityStatus): String {
    return when (status) {
        AirQualityStatus.GOOD -> "Воздух чистый, можно проветривать"
        AirQualityStatus.MODERATE -> "Воздух удовлетворительный"
        AirQualityStatus.POOR -> "Рекомендуется закрыть окна"
    }
}