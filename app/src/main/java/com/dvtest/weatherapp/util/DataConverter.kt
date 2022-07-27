package com.dvtest.weatherapp.util

import androidx.room.TypeConverter
import com.dvtest.weatherapp.model.Weather
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object DataConverter {
    @TypeConverter
    fun weatherStringToList(data: String?): List<Weather>? {
        if (data == null) {
            return emptyList()
        }

       var datas = Json.decodeFromString<List<Weather>>(data!!)
        return datas

    }

    @TypeConverter
    fun weatherListToString(objects: List<Weather>): String {
        var converted = Json.encodeToString(objects)
        return  converted
    }
    @TypeConverter
    fun weatherToList(data: String?): Weather? {
        if (data == null) {
            return Weather()
        }

       var datas = Json.decodeFromString<Weather>(data!!)
        return datas

    }

    @TypeConverter
    fun weatherToString(objects: Weather): String {
        var converted = Json.encodeToString(objects)
        return  converted
    }
}