package com.dvtest.weatherapp.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.json.JSONObject



@Serializable
data class Wind(
    @SerialName("deg")
    var deg: Int = 0, // 360
    @SerialName("speed")
    var speed: Double = 0.0 // 2.06
) {
    companion object {
        @JvmStatic
        fun buildFromJson(jsonObject: JSONObject?): Wind? {

            jsonObject?.run {
                return Wind(
                    optInt("deg"),
                    optDouble("speed")
                )
            }
            return null
        }
    }
}