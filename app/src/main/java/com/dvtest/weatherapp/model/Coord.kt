package com.dvtest.weatherapp.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.json.JSONObject



@Serializable
data class Coord(
    @SerialName("lat")
    var lat: Double = 0.0, // 57
    @SerialName("lon")
    var lon: Double = 0.0 // 10
) {
    companion object {
        @JvmStatic
        fun buildFromJson(jsonObject: JSONObject?): Coord? {

            jsonObject?.run {
                return Coord(
                    optDouble("lat"),
                    optDouble("lon")
                )
            }
            return null
        }
    }
}