package com.netooran.weather.weather

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.netooran.weather.R
import com.netooran.weather.persistence.Weather

class WeatherViewModel(private var weatherRepo: WeatherRepository) : ViewModel() {

    private var mCurrentWeather: LiveData<Weather> = weatherRepo.getCurrentWeather(LOCATION_ID)

    fun getCurrentWeather(): LiveData<Weather> = mCurrentWeather

    fun addWeather() {
        val weather = Weather(0, LOCATION_ID, "32°", "Sunny", R.drawable.ic_01_s)
        weatherRepo.insert(weather)
    }

    companion object {
        // Using a hardcoded value for simplicity
        const val LOCATION_ID = "Bangalore"
    }

    class ViewModelFactory(private val repository: WeatherRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WeatherViewModel::class.java))
                @Suppress(names = ["UNCHECKED_CAST"])
                return WeatherViewModel(repository) as T
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}