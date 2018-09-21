package com.netooran.weather.weather

import android.content.Context
import android.location.Location
import com.netooran.weather.RetrofitLiveData
import com.netooran.weather.persistence.WeatherDao
import com.netooran.weather.persistence.WeatherDatabase

class WeatherRepository(context: Context, private val apiService: WeatherApiService) {

    private val weatherDao: WeatherDao

    init {
        val db = WeatherDatabase.getInstance(context)
        weatherDao = db.weatherDao()
    }

    fun getWeather(location: Location): RetrofitLiveData<Model.Weather> =
            RetrofitLiveData(apiService.getWeather(
                    location.latitude,
                    location.longitude,
                    Model.Unit.METRIC.name.toLowerCase(),
                    WeatherApiService.apiKey))

}