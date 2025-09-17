package com.example.uppgift3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.uppgift3.viewmodel.WeatherViewModel
import com.squareup.picasso.Picasso
import java.util.Locale

class WeatherFragment : Fragment() {

    private val viewModel: WeatherViewModel by viewModels()

    private lateinit var progressBar: ProgressBar
    private lateinit var tvCityName: TextView
    private lateinit var tvTemperature: TextView
    private lateinit var tvWeatherDescription: TextView
    private lateinit var tvHumidity: TextView
    private lateinit var tvWindSpeed: TextView
    private lateinit var ivWeatherIcon: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)

        progressBar = view.findViewById(R.id.progressBar)
        tvCityName = view.findViewById(R.id.tv_city_name)
        tvTemperature = view.findViewById(R.id.tv_temperature)
        tvWeatherDescription = view.findViewById(R.id.tv_weather_description)
        tvHumidity = view.findViewById(R.id.tv_humidity)
        tvWindSpeed = view.findViewById(R.id.tv_wind_speed)
        ivWeatherIcon = view.findViewById(R.id.iv_weather_icon)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Aktivera back-knapp i ActionBar
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val cityName = arguments?.getString("cityName") ?: "Stockholm"

        // Observera LiveData från ViewModel
        viewModel.weatherData.observe(viewLifecycleOwner) { weather ->
            weather?.let { updateWeatherUI(it) }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            progressBar.visibility = if (loading) View.VISIBLE else View.GONE
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        // Ladda väderdata via ViewModel
        viewModel.loadWeatherData(cityName)
    }

    private fun updateWeatherUI(weather: WeatherResponse) {
        tvCityName.text = "${weather.cityName}, ${weather.sys.country}"
        tvTemperature.text = "${weather.main.temperature.toInt()}°C"
        tvWeatherDescription.text = weather.weather[0].description
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        tvHumidity.text = "Fuktighet: ${weather.main.humidity}%"
        tvWindSpeed.text = "Vind: ${weather.wind.speed} m/s"

        // Ladda ikon asynkront med Picasso (ingen blockering av UI)
        val iconUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}@2x.png"
        Picasso.get()
            .load(iconUrl)
            .resize(200, 200)          // Optional: minska storlek för snabbare rendering
            .centerInside()
            .into(ivWeatherIcon)
    }
}
