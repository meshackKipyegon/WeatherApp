package com.dvtest.weatherapp.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.json.JSONObject



@Serializable
data class Main(
    @SerialName("feels_like")
    var feelsLike: Double = 0.0, // 11.77
    @SerialName("humidity")
    var humidity: Int = 0, // 60
    @SerialName("pressure")
    var pressure: Int = 0, // 1027
    @SerialName("temp")
    var temp: Double = 0.0, // 12.86
    @SerialName("temp_max")
    var tempMax: Double = 0.0, // 13.33
    @SerialName("temp_min")
    var tempMin: Double = 0.0 // 11.88
) {
    companion object {
        @JvmStatic
        fun buildFromJson(jsonObject: JSONObject?): Main? {

            jsonObject?.run {
                return Main(
                    optDouble("feels_like"),
                    optInt("humidity"),
                    optInt("pressure"),
                    optDouble("temp"),
                    optDouble("temp_max"),
                    optDouble("temp_min")
                )
            }
            return null
        }
    }
}