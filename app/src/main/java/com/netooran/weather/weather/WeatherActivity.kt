package com.netooran.weather.weather

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat.checkSelfPermission
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.netooran.weather.Injections
import com.netooran.weather.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class WeatherActivity : AppCompatActivity() {
    private lateinit var viewModelFactory: WeatherViewModel.ViewModelFactory
    private lateinit var mModel: WeatherViewModel

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION: Int = 1
    private val CITY_NAME_INDEX = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        supportActionBar?.hide()

        viewModelFactory = Injections.provideViewModelFactory(this, WeatherApiService.create())
        mModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherViewModel::class.java)
        getWeather()
    }

    private fun getWeather() {
        if (checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(ACCESS_COARSE_LOCATION), MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION)

        fusedLocationClient.lastLocation
                .addOnFailureListener { exception -> Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show() }
                .addOnSuccessListener { location: Location ->
                    mModel.getCurrentWeather(location)
                            .observe(this, Observer {
                                if (it == null) {
                                    Log.e(this.javaClass.canonicalName, "::::: Something went wrong while fetching weather. 'Weather is null'")
                                    return@Observer
                                }
                                populateWeather(it)
                            })
                }
    }

    private fun getCity(location: Location): String =
            Geocoder(this, Locale.getDefault())
                    .getFromLocation(location.latitude, location.longitude, 1)[0]
                    .getAddressLine(CITY_NAME_INDEX).split(",")[1]

    private fun populateWeather(weather: Model.Weather?) {
        temperature.text = weather?.main?.temp.toString()
        weather_text.text = weather?.weather!![0].description
        weather_icon.background = getDrawable(getIcon(weather))
        location.text = weather.name
    }

    fun getIcon(weather: Model.Weather?) = R.drawable.ic_01_s // Fetch icon from openWeatherMap
}
