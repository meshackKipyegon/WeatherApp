package com.dvtest.weatherapp.ui.presenter

import com.dvtest.weatherapp.core.ResponseListener
import com.dvtest.weatherapp.model.dbmodels.CurrentWeatherEntity
import com.dvtest.weatherapp.model.dbmodels.ForecastEntity
import com.dvtest.weatherapp.ui.model.WeatherModel
import com.dvtest.weatherapp.ui.view.WeatherContract

class WeatherPresenter(var view: WeatherContract.view, var model: WeatherModel) :
    WeatherContract.presenter {
    override fun onDestroy() {}
    override fun requestWeather(latitude: String, longitude: String) {
        view.onShowLoading()
        var responseListener = object : ResponseListener {
            override fun onSuccess(jsonObject: Any?) {
                view.onHideLoading()
                view.onWeatherLoaded(jsonObject as CurrentWeatherEntity)
            }

            override fun onError(jsonObject: String?) {
                view.onHideLoading()
                view.onError(jsonObject!!)
            }
        }
        model.loadWeather(latitude, longitude, responseListener)
    }

    override fun requestForecast(latitude: String, longitude: String) {
        view.onShowLoading()
        var responseListener = object : ResponseListener {
            override fun onSuccess(jsonObject: Any?) {
                view.onHideLoading()
                view.onForecastLoaded(jsonObject as List<ForecastEntity>)
            }

            override fun onError(jsonObject: String?) {
                view.onHideLoading()
                view.onError(jsonObject!!)
            }
        }
        model.loadForecast(latitude, longitude, responseListener)
    }
}