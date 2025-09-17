package com.example.uppgift3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uppgift3.WeatherResponse
import com.example.uppgift3.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val repository = WeatherRepository()

    private val _weatherData = MutableLiveData<WeatherResponse?>()
    val weatherData: LiveData<WeatherResponse?> = _weatherData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun loadWeatherData(city: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getWeatherForCity(city)
                if (response != null) {
                    _weatherData.value = response
                    _errorMessage.value = null
                } else {
                    _errorMessage.value = "Kunde inte hitta väderdata för $city"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Nätverksfel: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadWeatherByLocation(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getWeatherByLocation(latitude, longitude)
                if (response != null) {
                    _weatherData.value = response
                    _errorMessage.value = null
                } else {
                    _errorMessage.value = "Kunde inte hämta väderdata för denna plats"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Nätverksfel: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
