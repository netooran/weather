package com.netooran.weather

data class Weather(val location: String,
                   val temperature: String,
                   val weatherText: String,
                   val iconId: Int)