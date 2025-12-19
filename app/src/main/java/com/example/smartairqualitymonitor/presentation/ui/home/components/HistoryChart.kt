package com.example.smartairqualitymonitor.presentation.ui.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.smartairqualitymonitor.domain.model.AirQuality
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HistoryChart(history: List<AirQuality>) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(250.dp)
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "История показаний PPM",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            if (history.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Нет данных для отображения",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            } else {
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    val width = size.width
                    val height = size.height

                    if (history.size < 2) return@Canvas

                    // Find min and max PPM for scaling
                    val maxPpm = history.maxOf { it.ppm }.toFloat()
                    val minPpm = history.minOf { it.ppm }.toFloat()
                    val range = maxPpm - minPpm

                    // Draw grid lines
                    for (i in 0..4) {
                        val y = height * i / 4
                        drawLine(
                            color = Color.Gray.copy(alpha = 0.2f),
                            start = Offset(0f, y),
                            end = Offset(width, y),
                            strokeWidth = 1f
                        )
                    }

                    // Draw the line chart
                    val path = Path()
                    val points = history.mapIndexed { index, reading ->
                        val x = (width / (history.size - 1)) * index
                        val normalizedValue = if (range > 0) {
                            (reading.ppm - minPpm) / range
                        } else {
                            0.5f
                        }
                        val y = height - (normalizedValue * height * 0.8f) - (height * 0.1f)
                        Offset(x, y)
                    }

                    path.moveTo(points[0].x, points[0].y)
                    for (i in 1 until points.size) {
                        path.lineTo(points[i].x, points[i].y)
                    }

                    drawPath(
                        path = path,
                        color = Color(0xFF2196F3),
                        style = Stroke(width = 4f)
                    )

                    // Draw points
                    points.forEach { point ->
                        drawCircle(
                            color = Color(0xFF2196F3),
                            radius = 6f,
                            center = point
                        )
                    }
                }

                // Show latest reading info
                history.lastOrNull()?.let { latest ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Последнее: ${latest.ppm} PPM",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = formatTime(latest.timestamp),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

private fun formatTime(timestamp: Long): String {
    val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return sdf.format(Date(timestamp))
}