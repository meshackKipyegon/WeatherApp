package com.dvtest.weatherapp.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Forecast(
    @SerialName("message")
    var message: String = "", // 3600
    @SerialName("cnt")
    var cnt: Int = 0, // 10000
    @SerialName("list")
    var weather: List<CurrentWeather>,
    @SerialName("city")
    var city: City = City()
)
