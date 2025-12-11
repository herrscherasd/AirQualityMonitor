package com.example.smartairqualitymonitor.data.api.repository

import com.example.smartairqualitymonitor.data.api.AirQualityApi
import com.example.smartairqualitymonitor.domain.model.AirQuality
import com.example.smartairqualitymonitor.domain.repository.IAirQualityRepository
import javax.inject.Inject

class AirQualityRepository @Inject constructor(
    private val api: AirQualityApi
) : IAirQualityRepository {
    override suspend fun fetchAirQuality(): List<AirQuality> {
        val response = api.getAirQualityData()
        return if (response.isSuccessful) {
            response.body()?.map { AirQuality(it.air_quality, it.timestamp) } ?: emptyList()
        } else {
            emptyList()
        }
    }
}