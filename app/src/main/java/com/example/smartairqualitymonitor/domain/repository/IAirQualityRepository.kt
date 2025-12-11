package com.example.smartairqualitymonitor.domain.repository

import com.example.smartairqualitymonitor.domain.model.AirQuality

interface IAirQualityRepository {
    suspend fun fetchAirQuality(): List<AirQuality>
}