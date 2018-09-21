package com.netooran.weather.weather

import com.google.gson.annotations.SerializedName

object Model {
    data class Weather(val id: Long,
                       val name: String,
                       val main: Reading,
                       val weather: List<Forecast>,
                       val coord: Coordinate)

    data class Coordinate(val lon: Double,
                          val lat: Double)

    data class Forecast(val id: Int,
                        val main: String,
                        val description: String,
                        val icon: String)

    data class Reading(val temp: Double,
                       val humidity: Int,
                       val pressure: Int,
                       @SerializedName("temp_min") val minTemp: Double,
                       @SerializedName("temp_max") val maxTemp: Double)

    enum class Unit {
        METRIC, IMPERIAL
    }
}