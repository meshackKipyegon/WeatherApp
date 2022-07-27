package com.dvtest.weatherapp.model.dbmodels

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dvtest.weatherapp.model.Coord
import com.dvtest.weatherapp.model.CurrentWeather
import com.dvtest.weatherapp.model.Main
import com.dvtest.weatherapp.model.Weather

@Entity(tableName = "Forecast")
data class ForecastEntity(
    @Embedded
    var lat: Coord,
    @Embedded
    var main: Main,
    @Embedded
    var weather:Weather,
    @PrimaryKey
    var id:Int,
    var dt_txt: String
    ){
    constructor(currentWeather: CurrentWeather) : this(
        lat = currentWeather.coord,
        main = currentWeather.main,
        weather = currentWeather.weather.get(0),
        id = currentWeather.id,
        dt_txt = currentWeather.dt_txt
    )
}