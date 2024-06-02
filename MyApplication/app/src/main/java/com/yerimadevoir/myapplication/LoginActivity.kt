package com.yerimadevoir.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.yerimadevoir.myapplication.R
import com.yerimadevoir.myapplication.auth.AuthenticationManager
class LoginActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    private lateinit var authenticationManager: AuthenticationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        authenticationManager = AuthenticationManager(this)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Vérifier les champs d'entrée
            if (email.isNotEmpty() && password.isNotEmpty()) {
                val role = authenticationManager.loginUser(email, password)

                if (role != null) {
                    // Connexion réussie
                    // Gérer la logique de redirection ou d'affichage de message de réussite
                } else {
                    // Échec de la connexion
                    // Gérer la logique d'affichage de message d'erreur
                }
            } else {
                // Afficher un message d'erreur si les champs sont vides
            }
        }
    }
}