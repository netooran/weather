package com.netooran.weather.weather

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.location.Location
import com.netooran.weather.RetrofitLiveData

class WeatherViewModel(private var weatherRepo: WeatherRepository) : ViewModel() {

    private lateinit var currentWeather: RetrofitLiveData<Model.Weather>

    fun getCurrentWeather(location: Location): RetrofitLiveData<Model.Weather> {
        currentWeather = weatherRepo.getWeather(location)
        return currentWeather
    }

    override fun onCleared() {
        currentWeather.cancel()
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