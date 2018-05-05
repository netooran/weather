package com.netooran.weather.weather

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.netooran.weather.Injections
import com.netooran.weather.R
import com.netooran.weather.persistence.Weather
import kotlinx.android.synthetic.main.activity_main.*

class WeatherActivity : AppCompatActivity() {
    private lateinit var viewModelFactory: WeatherViewModel.ViewModelFactory
    private lateinit var mModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        viewModelFactory = Injections.provideViewModelFactory(this)
        mModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherViewModel::class.java)
        mModel.getCurrentWeather().observe(this, Observer<Weather> {
            if (it == null) {
                mModel.addWeather()
                return@Observer
            }
            populateWeather(it)
        })
    }

    private fun populateWeather(weather: Weather) {
        temperature.text = weather.temperature
        weather_text.text = weather.weatherText
        weather_icon.background = getDrawable(weather.iconId)
        location.text = weather.location
    }
}
