package com.example.smartairqualitymonitor.presentation.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartairqualitymonitor.presentation.ui.home.components.*

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val airQuality by viewModel.airQuality.collectAsState()
    val history by viewModel.history.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "🌫️ Монитор качества воздуха",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        AirQualityCard(
            airQuality = airQuality,
            isLoading = isLoading
        )

        StatusIndicator(airQuality)

        HistoryChart(history)

        error?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Button(
            onClick = { viewModel.fetchLatestData() },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 16.dp),
            enabled = !isLoading
        ) {
            Text(if (isLoading) "Загрузка..." else "Обновить данные")
        }
    }
}