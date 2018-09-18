package com.netooran.weather.weather

import android.arch.lifecycle.LiveData
import android.content.Context
import android.os.AsyncTask
import com.netooran.weather.R
import com.netooran.weather.persistence.Weather
import com.netooran.weather.persistence.WeatherDao
import com.netooran.weather.persistence.WeatherDatabase

class WeatherRepository(context: Context) {

    private val weatherDao: WeatherDao
    private lateinit var currentWeather: LiveData<Weather>

    init {
        val db = WeatherDatabase.getInstance(context)
        weatherDao = db.weatherDao()
    }

    fun getCurrentWeather(location: String): LiveData<Weather> {
        currentWeather = weatherDao.getWeatherByLocation(location)
        if (currentWeather.value == null) insert(Weather(0, location, "32°", "Sunny", R.drawable.ic_01_s))
        return currentWeather
    }

    private fun insert(weather: Weather) {
        InsertAsyncTask(weatherDao).execute(weather)
    }

    private class InsertAsyncTask(private val mAsyncTaskDao: WeatherDao) : AsyncTask<Weather, Void, Void>() {

        override fun doInBackground(vararg params: Weather): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }
}