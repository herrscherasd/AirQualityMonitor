package com.example.smartairqualitymonitor.domain.model

data class AirQuality(
    val value: Int,
    val timestamp: Long = System.currentTimeMillis()
) {
    val status: AirQualityStatus
        get() = when {
            value < 500 -> AirQualityStatus.GOOD
            value < 1000 -> AirQualityStatus.MODERATE
            else -> AirQualityStatus.POOR
        }
}

enum class AirQualityStatus {
    GOOD, MODERATE, POOR
}