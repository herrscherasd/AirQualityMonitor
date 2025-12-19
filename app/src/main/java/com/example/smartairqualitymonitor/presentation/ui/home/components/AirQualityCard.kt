package com.example.smartairqualitymonitor.presentation.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartairqualitymonitor.domain.model.AirQuality
import com.example.smartairqualitymonitor.domain.model.QualityLevel

@Composable
fun AirQualityCard(
    airQuality: AirQuality?,
    isLoading: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = airQuality?.let { getQualityColor(it.getQualityLevel()) }
                ?: MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color.White)
                Text(
                    text = "Загрузка...",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge
                )
            } else if (airQuality != null) {
                // Quality Status
                Text(
                    text = getQualityText(airQuality.quality),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                // PPM Value (Main Display)
                Text(
                    text = "${airQuality.ppm} PPM",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )

                Divider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.White.copy(alpha = 0.3f)
                )

                // Additional Info
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    InfoColumn(
                        label = "Напряжение",
                        value = String.format("%.2f В", airQuality.voltage)
                    )
                    InfoColumn(
                        label = "Сырое значение",
                        value = airQuality.rawValue.toString()
                    )
                }

                // Quality Description
                Text(
                    text = getQualityDescription(airQuality.getQualityLevel()),
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.9f),
                    modifier = Modifier.padding(top = 8.dp)
                )
            } else {
                Text(
                    text = "Нет данных",
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
private fun InfoColumn(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.White.copy(alpha = 0.8f)
        )
        Text(
            text = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

private fun getQualityColor(level: QualityLevel): Color {
    return when (level) {
        QualityLevel.GOOD -> Color(0xFF4CAF50)      // Green
        QualityLevel.MODERATE -> Color(0xFFFFC107)  // Yellow
        QualityLevel.POOR -> Color(0xFFF44336)      // Red
        QualityLevel.UNKNOWN -> Color(0xFF9E9E9E)   // Gray
    }
}

private fun getQualityText(quality: String): String {
    return when (quality) {
        "GOOD" -> "Отлично"
        "MODERATE" -> "Умеренно"
        "POOR" -> "Плохо"
        else -> "Неизвестно"
    }
}

private fun getQualityDescription(level: QualityLevel): String {
    return when (level) {
        QualityLevel.GOOD -> "Качество воздуха хорошее. Воздух чистый."
        QualityLevel.MODERATE -> "Качество воздуха приемлемое. Может потребоваться проветривание."
        QualityLevel.POOR -> "Качество воздуха плохое. Требуется проветривание!"
        QualityLevel.UNKNOWN -> "Не удалось определить качество воздуха"
    }
}