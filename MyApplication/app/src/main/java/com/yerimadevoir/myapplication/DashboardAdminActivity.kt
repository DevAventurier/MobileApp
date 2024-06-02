package com.yerimadevoir.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yerimadevoir.myapplication.database.DatabaseHelper

class DashboardAdminActivity : AppCompatActivity() {
    private var calculateButton: Button? = null
    private var databaseHelper: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_admin)

        calculateButton = findViewById<Button>(R.id.calculateButton)

        calculateButton?.setOnClickListener {
            calculateNotes()
        }
    }

    private fun calculateNotes() {
        // Récupérer les données nécessaires pour le calcul des notes
        //val databaseHelper
        val notesList: List<String> = databaseHelper.getAllNotes() // Remplacez databaseHelper par votre propre gestionnaire de base de données

        // Effectuer le calcul des notes
        var totalScore = 0
        var averageScore = 0.0

        for (note in notesList) {
            val score = note.toIntOrNull() // Convertir la note en entier, si possible

            if (score != null) {
                totalScore += score
            }
        }

        if (notesList.isNotEmpty()) {
            averageScore = totalScore.toDouble() / notesList.size
        }

        // Afficher les résultats
        val message = "Total des notes : $totalScore\nMoyenne des notes : $averageScore"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}