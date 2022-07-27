package com.dvtest.weatherapp.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.json.JSONObject



@Serializable
data class Clouds(
    @SerialName("all")
    var all: Int = 0 // 29
) {
    companion object {
        @JvmStatic
        fun buildFromJson(jsonObject: JSONObject?): Clouds? {

            jsonObject?.run {
                return Clouds(
                    optInt("all")
                )
            }
            return null
        }
    }
}