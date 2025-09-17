package com.example.uppgift3

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class WelcomeFragment : Fragment() {

    private lateinit var btnStart: Button
    private lateinit var btnWeather: Button
    private lateinit var btnLogout: Button
    private lateinit var tvWelcome: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)

        btnStart = view.findViewById(R.id.btn_start)
        btnWeather = view.findViewById(R.id.btn_weather)
        btnLogout = view.findViewById(R.id.btn_logout)
        tvWelcome = view.findViewById(R.id.tv_welcome)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Aktivera back-knapp i ActionBar (ska gå tillbaka till login)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Sätt välkomstmeddelande
        val username = getSavedUsername()
        tvWelcome.text = "Välkommen, $username!"

        btnStart.setOnClickListener {
            navigateToLocations()
        }

        btnWeather.setOnClickListener {
            navigateToWeather()
        }

        btnLogout.setOnClickListener {
            logoutUser()
        }
    }

    private fun getSavedUsername(): String {
        val sharedPref = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        return sharedPref.getString("username", "användare") ?: "användare"
    }

    private fun navigateToLocations() {
        // Använd Navigation Components för typesäker navigation
        val action = WelcomeFragmentDirections.actionWelcomeFragmentToLocationsFragment()
        findNavController().navigate(action)
    }

    private fun navigateToWeather() {
        // Navigera direkt till weather med standardstad
        val action = WelcomeFragmentDirections.actionWelcomeFragmentToWeatherFragment()
        findNavController().navigate(action)
    }

    private fun logoutUser() {
        // Rensa inloggningsstatus
        val sharedPref = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        sharedPref.edit()
            .putBoolean("is_logged_in", false)
            .remove("username")
            .apply()

        // Navigera tillbaka till login fragment
        findNavController().popBackStack(R.id.loginFragment, false)
    }

    override fun onResume() {
        super.onResume()
        // Se till att back-knappen är synlig på welcome skärmen
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}