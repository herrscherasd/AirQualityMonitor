package com.example.smartairqualitymonitor.presentation.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smartairqualitymonitor.domain.model.AirQuality
import com.example.smartairqualitymonitor.presentation.utils.*

@Composable
fun AirQualityCard(
    airQuality: AirQuality?,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator()
                Text("Загрузка данных...")
            } else if (airQuality != null) {
                Text(
                    text = "${airQuality.value}",
                    style = MaterialTheme.typography.displayMedium,
                    color = getStatusColor(airQuality.status)
                )

                Text(
                    text = getStatusText(airQuality.status),
                    style = MaterialTheme.typography.headlineSmall,
                    color = getStatusColor(airQuality.status)
                )

                Text(
                    text = getDescription(airQuality.status),
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                Text("Нет данных")
            }
        }
    }
}