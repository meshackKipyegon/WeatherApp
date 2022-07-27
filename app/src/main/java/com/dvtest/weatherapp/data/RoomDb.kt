package com.dvtest.weatherapp.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dvtest.weatherapp.MainApp
import com.dvtest.weatherapp.data.dao.CurrentWeatherDao
import com.dvtest.weatherapp.data.dao.ForecastDao
import com.dvtest.weatherapp.model.dbmodels.CurrentWeatherEntity
import com.dvtest.weatherapp.model.dbmodels.ForecastEntity
import com.dvtest.weatherapp.util.DataConverter

class RoomDb {
    @Database(
        entities = [
            CurrentWeatherEntity::class,
            ForecastEntity::class,
        ],
        version = 1
    )
    @TypeConverters(DataConverter::class)
    abstract class WeatherDatabase : RoomDatabase() {
        abstract fun currentWeatherDao(): CurrentWeatherDao
        abstract fun ForecastDao(): ForecastDao

        companion object {
            private var appDbInstance: WeatherDatabase? = null// .addTypeConverter(new RConverter())

            // .addTypeConverter(new RConverterB())
            // @TypeConverters({RConverter.class, RConverterB.class})
            val appDatabase: WeatherDatabase?
                get() {
                    if (appDbInstance == null) {
                        appDbInstance = Room.databaseBuilder(
                            MainApp.context,
                            WeatherDatabase::class.java,
                            "weatherDb"
                        )
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries() // .addTypeConverter(new RConverter())
                            // .addTypeConverter(new RConverterB())
                            .build()
                    }
                    return appDbInstance
                }
        }


    }
}