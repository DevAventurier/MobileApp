package com.yerimadevoir.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yerimadevoir.myapplication.R


class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Récupération des informations de l'utilisateur connecté
        val intent = intent
        val role = intent.getStringExtra("role")

        // redirection vers le tableau de bord approprié
        if (role != null) {
            when (role) {
                "admin" -> {
                    val dashboardAdminIntent = Intent(this, DashboardAdminActivity::class.java)
                    startActivity(dashboardAdminIntent)
                    finish()
                }
                "enseignant" -> {
                    val dashboardEnseignantIntent = Intent(this, DashboardEnseignantActivity::class.java)
                    startActivity(dashboardEnseignantIntent)
                    finish()
                }
                "etudiant" -> {
                    val dashboardEtudiantIntent = Intent(this, DashboardEtudiantActivity::class.java)
                    startActivity(dashboardEtudiantIntent)
                    finish()
                }
            }
        }
    }
}