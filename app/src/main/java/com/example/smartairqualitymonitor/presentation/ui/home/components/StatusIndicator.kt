package com.example.smartairqualitymonitor.presentation.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smartairqualitymonitor.domain.model.AirQuality
import com.example.smartairqualitymonitor.presentation.utils.getStatusColor

@Composable
fun StatusIndicator(
    airQuality: AirQuality?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        listOf("Хорошо", "Умеренно", "Плохо").forEachIndexed { index, label ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    shape = CircleShape,
                    color = if (airQuality?.status?.ordinal == index) {
                        getStatusColor(airQuality.status)
                    } else {
                        MaterialTheme.colorScheme.surfaceVariant
                    },
                    modifier = Modifier.size(24.dp)
                ) {}

                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}