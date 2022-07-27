package com.dvtest.weatherapp.model


import androidx.room.Ignore
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.json.JSONObject



@Serializable
data class Weather(
    @SerialName("description")
    var description: String = "", // scattered clouds
    @SerialName("icon")
    var icon: String = "", // 03d
    @Ignore
    @SerialName("id")
    var id: Int = 0, // 802
    @SerialName("main")
    var main: String = "" // Clouds
//    @SerialName("dt_txt")
//    var dt_txt: String = "" // Clouds
) {
    companion object {
        @JvmStatic
        fun buildFromJson(jsonObject: JSONObject?): Weather? {

            jsonObject?.run {
                return Weather(
                    optString("description"),
                    optString("icon"),
                    optInt("id"),
                    optString("main")
                )
            }
            return null
        }
    }
}