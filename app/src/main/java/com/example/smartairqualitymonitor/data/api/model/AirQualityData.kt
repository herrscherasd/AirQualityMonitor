package com.example.smartairqualitymonitor.data.api.model

data class AirQualityData(
    val air_quality: Int,
    val timestamp: Long = System.currentTimeMillis()
)