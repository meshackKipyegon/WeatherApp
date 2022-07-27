package com.dvtest.weatherapp.data.dao

import androidx.room.*
import com.dvtest.weatherapp.model.dbmodels.ForecastEntity

@Dao
interface ForecastDao {

    @Query("SELECT * FROM Forecast")
    fun getCurrentWeather(): List<ForecastEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentWeather(currentWeatherEntity: ForecastEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(currentWeatherEntity: List<ForecastEntity>)

    @Transaction
    fun deleteAndInsert(currentWeatherEntity: ForecastEntity) {
        deleteCurrentWeather()
        insertCurrentWeather(currentWeatherEntity)
    }

    @Query("DELETE FROM Forecast")
    fun deleteCurrentWeather()

    @Query("Select count(*) from Forecast")
    fun getCount(): Int
}
