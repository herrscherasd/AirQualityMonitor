package com.example.smartairqualitymonitor.presentation.ui.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.smartairqualitymonitor.domain.model.AirQuality
import com.example.smartairqualitymonitor.presentation.utils.getStatusColor

@Composable
fun HistoryChart(
    history: List<AirQuality>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "История (последние ${history.size} замеров)",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (history.isNotEmpty()) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
                val maxValue = history.maxOf { it.value }.toFloat()
                val minValue = history.minOf { it.value }.toFloat()
                val range = maxValue - minValue

                val xStep = size.width / (history.size - 1)
                val yScale = size.height / (if (range > 0) range else 1f)

                val points = history.mapIndexed { index, data ->
                    Offset(
                        x = index * xStep,
                        y = size.height - ((data.value - minValue) * yScale)
                    )
                }

                for (i in 0 until points.size - 1) {
                    drawLine(
                        color = getStatusColor(history[i].status),
                        start = points[i],
                        end = points[i + 1],
                        strokeWidth = 4f,
                        cap = StrokeCap.Round
                    )
                }

                points.forEach { point ->
                    drawCircle(
                        color = Color.White,
                        radius = 6f,
                        center = point,
                        style = Stroke(width = 2f)
                    )
                }
            }
        } else {
            Text("Нет данных для графика")
        }
    }
}