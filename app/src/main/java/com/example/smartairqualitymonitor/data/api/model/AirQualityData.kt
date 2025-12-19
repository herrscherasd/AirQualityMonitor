    package com.example.smartairqualitymonitor.data.api.model

    import com.google.gson.annotations.SerializedName

    data class AirQualityData(
        @SerializedName("raw_value")
        val rawValue: Int,

        @SerializedName("voltage")
        val voltage: Float,

        @SerializedName("ppm")
        val ppm: Int,

        @SerializedName("quality")
        val quality: String,

        @SerializedName("timestamp")
        val timestamp: Long
    )