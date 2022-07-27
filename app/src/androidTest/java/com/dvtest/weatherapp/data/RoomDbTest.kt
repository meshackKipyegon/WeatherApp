package com.dvtest.weatherapp.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dvtest.weatherapp.data.dao.CurrentWeatherDao
import com.dvtest.weatherapp.model.Coord
import com.dvtest.weatherapp.model.Main
import com.dvtest.weatherapp.model.Weather
import com.dvtest.weatherapp.model.dbmodels.CurrentWeatherEntity
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class RoomDbTest : TestCase() {
    private lateinit var db: RoomDb.WeatherDatabase
    private lateinit var dao: CurrentWeatherDao

    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, RoomDb.WeatherDatabase::class.java).build()
        dao = db.currentWeatherDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeAndReadCurrentWeather() {
        //current Weather Entity takes 6 input paramaters.
        // Coord,Main and Weather objects, id as integer,lastupdated time as string and name of city
        var coord = Coord(1.0, 1.0)
        var main = Main(11.2, 60, 0, 10.0, 12.0, 10.2)
        var weather = Weather("cloudy", "0d", 0, "Cloudy")
        var id = 10
        var lastUpdated = Date().toString()
        var nameOfCity = "Cape Town"
        var currentWeatherEntity =
            CurrentWeatherEntity(coord, main, weather, id, lastUpdated, nameOfCity)
        dao.insertCurrentWeather(currentWeatherEntity)
        var fromDb = dao.getCurrentWeather().get(0)
        assertEquals(fromDb, currentWeatherEntity)
    }
}