package com.netooran.weather

import android.content.Context
import com.netooran.weather.weather.WeatherRepository
import com.netooran.weather.weather.WeatherViewModel

object Injections {

    private fun provideWeatherRepository(context: Context): WeatherRepository = WeatherRepository(context)

    fun provideViewModelFactory(context: Context): WeatherViewModel.ViewModelFactory {
        val dataSource = provideWeatherRepository(context)
        return WeatherViewModel.ViewModelFactory(dataSource)
    }
}