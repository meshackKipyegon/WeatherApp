package com.dvtest.weatherapp.repository

import com.dvtest.weatherapp.MainApp
import com.dvtest.weatherapp.data.dao.ForecastDao
import com.dvtest.weatherapp.model.dbmodels.ForecastEntity
import com.dvtest.weatherapp.network.ApiClass
import com.dvtest.weatherapp.network.WeatherApi
import com.dvtest.weatherapp.util.NetworkUtil
import io.ktor.client.features.*


class ForecastRepository {
    var context = MainApp.context
    suspend fun makeForecastRequest(
        lat: String, lon: String, units: String, forecastDao: ForecastDao
    ): Any {
        try {
            if (NetworkUtil.isNetworkAvailable()) {
                var data = WeatherApi(ApiClass().getClient()).getForecast(lat, lon, units)
                var forecastlist = ArrayList<ForecastEntity>()
                for (i in 0 until data.cnt) {
                    var dataObj = data.weather.get(i)
                    var entity = ForecastEntity(
                        data.city.coord,
                        dataObj.main,
                        dataObj.weather.get(0),
                        i + 1,
                        dataObj.dt_txt
                    )
                    forecastlist.add(entity)
                    forecastDao.insertCurrentWeather(entity)

                }
                return forecastDao.getCurrentWeather()
            } else {
                var localdata = forecastDao.getCurrentWeather()
                if (localdata.isEmpty()) {
                    return "No Internet Connection"
                } else {
                    return localdata
                }
            }

        } catch (e: ClientRequestException) {
            return e
        } catch (e: Exception) {
            return e
        }
    }
}
