package com.netooran.weather

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val weather = Weather("Bangalore", "31°", "Mostly clear", R.drawable.ic_01_s)
        populateWeather(weather)
    }

    private fun populateWeather(weather: Weather) {
        temperature.text = weather.temperature
        weather_text.text = weather.weatherText
        weather_icon.background = getDrawable(weather.iconId)
        location.text = weather.location
    }
}
