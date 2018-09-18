package com.netooran.weather.weather

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.netooran.weather.R
import com.netooran.weather.persistence.Weather

class WeatherViewModel(private var weatherRepo: WeatherRepository) : ViewModel() {

    fun getCurrentWeather(location: String): LiveData<Weather> = weatherRepo.getCurrentWeather(location)

    class ViewModelFactory(private val repository: WeatherRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WeatherViewModel::class.java))
                @Suppress(names = ["UNCHECKED_CAST"])
                return WeatherViewModel(repository) as T
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}