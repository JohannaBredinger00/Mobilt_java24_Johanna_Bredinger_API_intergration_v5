package com.example.uppgift3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class SearchFragment : Fragment() {

    private lateinit var etSearch: EditText
    private lateinit var btnSearch: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        // Initiera vyer
        etSearch = view.findViewById(R.id.et_search)
        btnSearch = view.findViewById(R.id.btn_search)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSearch.setOnClickListener {
            val searchQuery = etSearch.text.toString().trim()
            if (searchQuery.isNotEmpty()) {
                val bundle = Bundle()
                bundle.putString("cityName", searchQuery)
                findNavController().navigate(R.id.action_searchFragment_to_weatherFragment, bundle)
            } else {
                etSearch.error = "Ange en stad"
            }
        }
    }
}