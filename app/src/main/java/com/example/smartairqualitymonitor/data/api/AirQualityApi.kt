package com.example.smartairqualitymonitor.data.api

import com.example.smartairqualitymonitor.data.api.model.AirQualityData
import retrofit2.Response
import retrofit2.http.GET

interface AirQualityApi {
    @GET("data")
    suspend fun getAirQualityData(): Response<List<AirQualityData>>
}