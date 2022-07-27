package com.dvtest.weatherapp.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.json.JSONObject



@Serializable
data class Sys(
    @SerialName("country")
    var country: String = "", // DK
    @SerialName("id")
    var id: Int = 0, // 2019789
    @SerialName("sunrise")
    var sunrise: Int = 0, // 1648271167
    @SerialName("sunset")
    var sunset: Int = 0, // 1648316709
    @SerialName("type")
    var type: Int = 0 // 2
) {
    companion object {
        @JvmStatic
        fun buildFromJson(jsonObject: JSONObject?): Sys? {

            jsonObject?.run {
                return Sys(
                    optString("country"),
                    optInt("id"),
                    optInt("sunrise"),
                    optInt("sunset"),
                    optInt("type")
                )
            }
            return null
        }
    }
}