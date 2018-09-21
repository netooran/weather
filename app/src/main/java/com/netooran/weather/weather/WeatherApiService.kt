package com.netooran.weather.weather

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    fun getWeather(@Query("lat") latitude: Double,
                   @Query("lon") longitude: Double,
                   @Query("units") unit: String,
                   @Query("apikey") apiKey: String): Call<Model.Weather>

    companion object Factory {

        private const val baseUrl = "http://api.openweathermap.org/data/2.5/"
        const val apiKey = "a91eeeb33c74577181da8b76152394b4"

        fun create(): WeatherApiService {

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)

            val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()

            return retrofit.create(WeatherApiService::class.java)
        }
    }
}