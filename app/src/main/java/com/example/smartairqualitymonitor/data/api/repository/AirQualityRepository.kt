package com.example.smartairqualitymonitor.data.api.repository

import com.example.smartairqualitymonitor.data.api.AirQualityApi
import com.example.smartairqualitymonitor.domain.model.AirQuality
import com.example.smartairqualitymonitor.domain.repository.IAirQualityRepository
import javax.inject.Inject

class AirQualityRepository @Inject constructor(
    private val api: AirQualityApi
) : IAirQualityRepository {

    override suspend fun fetchAirQuality(): List<AirQuality> {
        return try {
            val response = api.getAirQualityData()
            if (response.isSuccessful) {
                response.body()?.let { data ->
                    listOf(
                        AirQuality.fromESP32(
                            rawValue = data.rawValue,
                            voltage = data.voltage,
                            ppm = data.ppm,
                            quality = data.quality,
                            timestamp = data.timestamp
                        )
                    )
                } ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}