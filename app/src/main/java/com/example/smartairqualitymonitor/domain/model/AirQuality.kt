package com.example.smartairqualitymonitor.domain.model

data class AirQuality(
    val airQuality: Int,
    val timestamp: Long = System.currentTimeMillis(),
    val rawValue: Int = 0,
    val voltage: Float = 0f,
    val ppm: Int = 0,
    val quality: String = "UNKNOWN"
) {
    companion object {
        fun fromESP32(
            rawValue: Int,
            voltage: Float,
            ppm: Int,
            quality: String,
            timestamp: Long
        ): AirQuality {
            return AirQuality(
                airQuality = ppm,
                timestamp = timestamp,
                rawValue = rawValue,
                voltage = voltage,
                ppm = ppm,
                quality = quality
            )
        }
    }

    fun getQualityLevel(): QualityLevel {
        return when (quality) {
            "GOOD" -> QualityLevel.GOOD
            "MODERATE" -> QualityLevel.MODERATE
            "POOR" -> QualityLevel.POOR
            else -> QualityLevel.UNKNOWN
        }
    }
}

enum class QualityLevel {
    GOOD, MODERATE, POOR, UNKNOWN
}