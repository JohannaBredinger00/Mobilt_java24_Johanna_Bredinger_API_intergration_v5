package com.example.uppgift3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class LocationsFragment : Fragment() {

    private lateinit var btnStockholm: Button
    private lateinit var btnGothenburg: Button
    private lateinit var btnMalmo: Button
    private lateinit var btnSearchNew: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_locations, container, false)

        btnStockholm = view.findViewById(R.id.btn_stockholm)
        btnGothenburg = view.findViewById(R.id.btn_gothenburg)
        btnMalmo = view.findViewById(R.id.btn_malmo)
        btnSearchNew = view.findViewById(R.id.btn_search_new)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Aktivera back-knapp i ActionBar (ska gå tillbaka till welcome)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnStockholm.setOnClickListener {
            navigateToWeather("Stockholm")
        }

        btnGothenburg.setOnClickListener {
            navigateToWeather("Göteborg")
        }

        btnMalmo.setOnClickListener {
            navigateToWeather("Malmö")
        }

        btnSearchNew.setOnClickListener {
            navigateToSearch()
        }
    }

    private fun navigateToWeather(city: String) {
        // Använd Safe Args för typesäker navigation
       // val action = LocationsFragmentDirections.actionLocationsFragmentToWeatherFragment()
        //action.cityName = city
        val bundle = Bundle()
        bundle.putString("cityName", city)
        findNavController().navigate(R.id.action_locationsFragment_to_weatherFragment, bundle)
    }

    private fun navigateToSearch() {
        // Navigera till search fragment med Safe Args
        //val action = LocationsFragmentDirections.actionLocationsFragmentToSearchFragment()
        findNavController().navigate(R.id.action_locationsFragment_to_searchFragment)
    }

    override fun onResume() {
        super.onResume()
        // Se till att back-knappen är synlig
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}