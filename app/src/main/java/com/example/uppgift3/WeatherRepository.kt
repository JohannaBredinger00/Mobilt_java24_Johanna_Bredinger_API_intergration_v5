package com.example.uppgift3.repository

import com.example.uppgift3.RetrofitClient
import com.example.uppgift3.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository {

    suspend fun getWeatherForCity(city: String): WeatherResponse? {
        return try {
            withContext(Dispatchers.IO) {
                val response = RetrofitClient.weatherApiService.getCurrentWeather(city)
                if (response.isSuccessful) {
                    response.body()
                } else {
                    null
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getWeatherByLocation(latitude: Double, longitude: Double): WeatherResponse? {
        return try {
            withContext(Dispatchers.IO) {
                val response = RetrofitClient.weatherApiService.getCurrentWeatherByLocation(latitude, longitude)
                if (response.isSuccessful) {
                    response.body()
                } else {
                    null
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
