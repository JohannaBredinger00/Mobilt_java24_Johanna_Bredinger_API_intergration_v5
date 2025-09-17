package com.example.uppgift3

import com.google.gson.annotations.SerializedName

data class WeatherResponse (
    @SerializedName("name")
    val cityName: String,

    @SerializedName("main")
    val main: MainData,

    @SerializedName("weather")
    val weather: List<WeatherInfo>,

    @SerializedName("wind")
    val wind: WindData,

    @SerializedName("sys")
    val sys: SysData,

    @SerializedName("dt")
    val dataTime: Long,

    @SerializedName("timezone")
    val timezone: Int
)

data class MainData(
    @SerializedName("temp")
    val temperature: Double,

    @SerializedName("feels_like")
    val feelsLike: Double,

    @SerializedName("temp_min")
    val tempMin: Double,

    @SerializedName("temp_max")
    val tempMax: Double,

    @SerializedName("pressure")
    val pressure: Int,

    @SerializedName("humidity")
    val humidity: Int
)

data class WeatherInfo(
    @SerializedName("id")
    val id: Int,

    @SerializedName("main")
    val main: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("icon")
    val icon: String
)

data class WindData(
    @SerializedName("speed")
    val speed: Double,

    @SerializedName("deg")
    val degree: Int
)

data class SysData(
    @SerializedName("country")
    val country: String,

    @SerializedName("sunrise")
    val sunrise: Long,

    @SerializedName("sunset")
    val sunset: Long
)


