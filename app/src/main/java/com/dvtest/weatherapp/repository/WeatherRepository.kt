package com.dvtest.weatherapp.repository

import com.dvtest.weatherapp.MainApp
import com.dvtest.weatherapp.data.dao.CurrentWeatherDao
import com.dvtest.weatherapp.model.dbmodels.CurrentWeatherEntity
import com.dvtest.weatherapp.network.ApiClass
import com.dvtest.weatherapp.network.WeatherApi
import com.dvtest.weatherapp.util.NetworkUtil
import io.ktor.client.features.*

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

class WeatherRepository {
    var context = MainApp.context
    suspend fun makeWeatherRequest(
        lat: String, lon: String, units: String, weatherDao: CurrentWeatherDao
    ): Any {
        try {
            if (NetworkUtil.isNetworkAvailable()) {
                var data = WeatherApi(ApiClass().getClient()).getCurrentWeather(lat, lon, units)
                var currentWeatherEntity = CurrentWeatherEntity(data)
                var dbinsert = weatherDao.insertCurrentWeather(currentWeatherEntity)
                var localData = weatherDao.getCurrentWeather().get(0)
                return localData

            } else {
                var localdata = weatherDao.getCurrentWeather()
                if (localdata.isEmpty()) {
                    return "No Internet Connection"
                } else {
                    return localdata[0]
                }
            }

        } catch (e: ClientRequestException) {
            return e
        } catch (e: Exception) {
            return e
        }
    }
}
