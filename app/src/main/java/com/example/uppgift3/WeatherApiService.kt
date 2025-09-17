package com.example.uppgift3

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
        @Query("lang") language: String = "se",
        @Query("appid") apiKey: String = "45af3ba9ed858533b3351d742f5dab5b"
    ): Response<WeatherResponse>

    @GET("data/2.5/weather")
    suspend fun getCurrentWeatherByLocation(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric",
        @Query("lang") language: String = "se",
        @Query("appid") apiKey: String = "45af3ba9ed858533b3351d742f5dab5b"
    ): Response<WeatherResponse>

}
