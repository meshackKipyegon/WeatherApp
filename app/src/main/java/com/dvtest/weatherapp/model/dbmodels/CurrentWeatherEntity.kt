package com.dvtest.weatherapp.model.dbmodels

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dvtest.weatherapp.model.Coord
import com.dvtest.weatherapp.model.CurrentWeather
import com.dvtest.weatherapp.model.Main
import com.dvtest.weatherapp.model.Weather
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "CurrentWeather")
@Serializable
data class CurrentWeatherEntity(
    @Embedded
    var lat: Coord,
    @Embedded
    var main: Main,
    @Embedded
    var weather: Weather,
    @PrimaryKey
    var id: Int,
    var lastUpdated: String,
    var name: String
){
    constructor(currentWeather: CurrentWeather) : this(
        lat = currentWeather.coord,
        main = currentWeather.main,
        weather = currentWeather.weather.get(0),
        id = currentWeather.id,
        lastUpdated = SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Date()),
        name = currentWeather.name
    )
    fun getDate():String{
        return SimpleDateFormat("yyyy-MM-DD:hhmmss").toLocalizedPattern().format(Date())
    }
}