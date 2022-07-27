package com.dvtest.weatherapp.ui.model

import com.dvtest.weatherapp.core.ResponseListener
import com.dvtest.weatherapp.data.RoomDb
import com.dvtest.weatherapp.repository.ForecastRepository
import com.dvtest.weatherapp.repository.WeatherRepository
import com.dvtest.weatherapp.ui.view.WeatherContract
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherModel : WeatherContract.model {
    var localDb = RoomDb.WeatherDatabase.appDatabase
    override fun loadWeather(
        latitude: String,
        longitude: String,
        responseListener: ResponseListener
    ) {
        var currentWeatherDao = localDb!!.currentWeatherDao()
//        CoroutineScope(Dispatchers.IO).launch {
        GlobalScope.launch {
            var data = WeatherRepository().makeWeatherRequest(
                latitude,
                longitude,
                "metric",
                currentWeatherDao
            )
            if (data.toString().trim().toLowerCase().equals("no internet connection")) {
                responseListener.onError("Please Check Your Internet Connection")
            } else
                if (data is Exception) {
                    responseListener.onError(data.message.toString())
                } else {
                    responseListener.onSuccess(data)
                }
        }
    }

    override fun loadForecast(
        latitude: String,
        longitude: String,
        responseListener: ResponseListener
    ) {
        var forecastDao = localDb!!.ForecastDao()
//        CoroutineScope(Dispatchers.IO).launch {
        GlobalScope.launch {
            var data = ForecastRepository().makeForecastRequest(
                latitude,
                longitude,
                "metric",
                forecastDao
            )
            if (data.toString().trim().toLowerCase().equals("no internet connection")) {
                responseListener.onError("Please Check Your Internet Connection")
            } else
                if (data is Exception) {
                    responseListener.onError(data.message.toString())
                } else {
                    responseListener.onSuccess(data)
                }
        }
    }
}
