package com.dvtest.weatherapp.network


import com.dvtest.weatherapp.core.Constants
import com.dvtest.weatherapp.model.CurrentWeather
import com.dvtest.weatherapp.model.Forecast
import io.ktor.client.*
import io.ktor.client.request.*

class WeatherApi(private val client: HttpClient) {
    suspend fun getCurrentWeather(
        lat: String, lon: String, units: String
    ): CurrentWeather = ApiClass().getClient()
        .get(Constants.BaseUrl+"weather?lat=$lat&lon=$lon&units=$units")

    suspend fun getForecast(
        lat: String, lon: String, units: String
    ): Forecast =
        client.get(Constants.BaseUrl+"forecast?lat=$lat&lon=$lon&units=$units")

}