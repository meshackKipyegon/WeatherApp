package com.dvtest.weatherapp.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.json.JSONObject

@Serializable
data class CurrentWeather(
    @SerialName("base")
    var base: String = "", // stations
    @SerialName("clouds")
    var clouds: Clouds = Clouds(),
    @SerialName("cod")
    var cod: Int = 0, // 200
    @SerialName("coord")
    var coord: Coord = Coord(),
    @SerialName("dt")
    var dt: Int = 0, // 1648291698
    @SerialName("id")
    var id: Int = 0, // 2621449
    @SerialName("main")
    var main: Main = Main(),
    @SerialName("name")
    var name: String = "", // Gistrup
    @SerialName("sys")
    var sys: Sys = Sys(),
    @SerialName("timezone")
    var timezone: Int = 0, // 3600
    @SerialName("visibility")
    var visibility: Int = 0, // 10000
    @SerialName("weather")
    var weather: List<Weather>,
    @SerialName("wind")
    var wind: Wind = Wind(),
    @SerialName("dt_txt")
    var dt_txt: String = "", // 1648291698
) {

    companion object {
        @JvmStatic
        fun buildFromJson(jsonObject: JSONObject?): CurrentWeather? {

            jsonObject?.run {
                return CurrentWeather(
                    optString("base"),
                    Clouds.buildFromJson(optJSONObject("clouds"))!!,
                    optInt("cod"),
                    Coord.buildFromJson(optJSONObject("coord"))!!,
                    optInt("dt"),
                    optInt("id"),
                    Main.buildFromJson(optJSONObject("main"))!!,
                    optString("name"),
                    Sys.buildFromJson(optJSONObject("sys"))!!,
                    optInt("timezone"),
                    optInt("visibility"),
                    ArrayList<Weather>().apply {
                        optJSONArray("weather")?.let {
                            for (i in 0 until it.length()) {
                                this.add(Weather.buildFromJson(it.getJSONObject(i))!!)
                            }
                        }
                    },
                    Wind.buildFromJson(optJSONObject("wind"))!!,
                    optString("dt_txt")
                )
            }
            return null
        }
    }
}