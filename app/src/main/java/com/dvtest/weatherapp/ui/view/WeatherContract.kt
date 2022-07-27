package com.dvtest.weatherapp.ui.view

import com.dvtest.weatherapp.core.BaseContract
import com.dvtest.weatherapp.core.ResponseListener
import com.dvtest.weatherapp.model.dbmodels.CurrentWeatherEntity
import com.dvtest.weatherapp.model.dbmodels.ForecastEntity

interface WeatherContract {
    interface view : BaseContract.view {
        fun onWeatherLoaded(currentWeatherEntity: CurrentWeatherEntity)
        fun onForecastLoaded(forecastEntity: List<ForecastEntity>)
    }

    interface presenter : BaseContract.presenter {
        fun requestWeather(latitude: String, longitude: String)
        fun requestForecast(latitude: String, longitude: String)
    }

    interface model {
        fun loadWeather(latitude: String, longitude: String, responseListener: ResponseListener)
        fun loadForecast(latitude: String, longitude: String, responseListener: ResponseListener)
    }
}