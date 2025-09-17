package com.example.uppgift3

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class LoginFragment : Fragment() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        etUsername = view.findViewById(R.id.et_username)
        etPassword = view.findViewById(R.id.et_password)
        btnLogin = view.findViewById(R.id.btn_login)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Dölj back-knappen på login skärmen
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        // Kolla om användaren redan är inloggad
        checkIfAlreadyLoggedIn()

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (validateLogin(username, password)) {
                // Spara inloggningsstatus
                saveLoginStatus(true)

                // Navigera till WelcomeFragment med Navigation Components
                navigateToWelcomeFragment()
            } else {
                showLoginError()
            }
        }
    }

    private fun checkIfAlreadyLoggedIn() {
        val sharedPref = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("is_logged_in", false)

        if (isLoggedIn) {
            // Om redan inloggad, navigera direkt till WelcomeFragment
            navigateToWelcomeFragment()
        }
    }

    private fun saveLoginStatus(isLoggedIn: Boolean) {
        val sharedPref = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        sharedPref.edit()
            .putBoolean("is_logged_in", isLoggedIn)
            .apply()
    }

    private fun navigateToWelcomeFragment() {
        // Använd Navigation Components för att navigera
        val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
        findNavController().navigate(action)
    }

    private fun validateLogin(username: String, password: String): Boolean {
        // Enkel validering - i en riktig app ska detta vara säkrare!
        return username.isNotBlank() && password.isNotBlank() &&
                (username == "user" && password == "password" ||
                        username == "admin" && password == "admin123")
    }

    private fun showLoginError() {
        Toast.makeText(
            requireContext(),
            "Fel användarnamn eller lösenord. Försök med 'user'/'password' eller 'admin'/'admin123'",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onResume() {
        super.onResume()
        // Se till att back-knappen är dold på login skärmen
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}