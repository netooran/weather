package com.netooran.weather

import android.content.Context
import com.netooran.weather.weather.WeatherApiService
import com.netooran.weather.weather.WeatherRepository
import com.netooran.weather.weather.WeatherViewModel

object Injections {

    private fun provideWeatherRepository(context: Context, apiService: WeatherApiService): WeatherRepository =
            WeatherRepository(context, apiService)

    fun provideViewModelFactory(context: Context, apiService: WeatherApiService): WeatherViewModel.ViewModelFactory {
        val dataSource = provideWeatherRepository(context, apiService)
        return WeatherViewModel.ViewModelFactory(dataSource)
    }
}