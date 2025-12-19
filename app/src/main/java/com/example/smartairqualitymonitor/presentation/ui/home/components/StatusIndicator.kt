package com.example.smartairqualitymonitor.presentation.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.smartairqualitymonitor.domain.model.AirQuality
import com.example.smartairqualitymonitor.domain.model.QualityLevel

@Composable
fun StatusIndicator(airQuality: AirQuality?) {
    if (airQuality == null) return

    Row(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        StatusDot(
            label = "Хорошо",
            isActive = airQuality.getQualityLevel() == QualityLevel.GOOD,
            color = Color(0xFF4CAF50)
        )
        StatusDot(
            label = "Умеренно",
            isActive = airQuality.getQualityLevel() == QualityLevel.MODERATE,
            color = Color(0xFFFFC107)
        )
        StatusDot(
            label = "Плохо",
            isActive = airQuality.getQualityLevel() == QualityLevel.POOR,
            color = Color(0xFFF44336)
        )
    }
}

@Composable
private fun StatusDot(
    label: String,
    isActive: Boolean,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(if (isActive) 24.dp else 16.dp)
                .clip(CircleShape)
                .background(if (isActive) color else color.copy(alpha = 0.3f))
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = if (isActive) color else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}