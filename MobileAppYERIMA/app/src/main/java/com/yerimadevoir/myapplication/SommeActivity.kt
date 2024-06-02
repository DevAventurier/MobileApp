package com.yerimadevoir.myapplication

import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SommeActivity : AppCompatActivity() {

    private lateinit var editTextNombre1: EditText
    private lateinit var editTextNombre2: EditText
    private lateinit var textViewResultat: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_somme)

        editTextNombre1 = findViewById(R.id.TextNombre1)
        editTextNombre2 = findViewById(R.id.editTextNombre2)
        textViewResultat = findViewById(R.id.textViewResultat)
        val buttonCalculer: Button = findViewById(R.id.buttonCalculer)

        buttonCalculer.setOnClickListener {
            calculerSomme()
        }
    }

    private fun calculerSomme() {
        try {
            val nombre1: Int = editTextNombre1.text.toString().toInt()
            val nombre2: Int = editTextNombre2.text.toString().toInt()

            val somme = nombre1 + nombre2

            val resultat: String = when {
                somme in 0..10 -> "Médiocre"
                somme in 11..12 -> "Passable"
                somme in 13..14 -> "À bien"
                somme in 15..16 -> "Bien"
                somme > 16 -> "Très Bien"
                else -> "Il semblerait quil ait un probleme"
            }

            textViewResultat.text = "Résultat : $resultat"

        } catch (e: NumberFormatException) {
            // Gérer l'exception si la conversion en nombre échoue
            textViewResultat.text = "Veuillez saisir des chiffres valides."
        }
    }
}
