package com.yerimadevoir.myapplication

import android.R
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.yerimadevoir.myapplication.database.DatabaseHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class DashboardEtudiantActivity : AppCompatActivity() {
    private var viewNotesButton: Button? = null
    private var resultTextView: TextView? = null
    private var databaseHelper: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_etudiant)
        viewNotesButton = findViewById<Button>(R.id.viewNotesButton)
        resultTextView = findViewById<TextView>(R.id.resultTextView)
        databaseHelper = DatabaseHelper(this)
        viewNotesButton.setOnClickListener(View.OnClickListener {
            val notesList: List<String> = databaseHelper!!.allNotes
            //  liste des notes récupérées afficher dans un RecyclerView
            //  référence au RecyclerView depuis notre mise en page
            val notesRecyclerView: RecyclerView = findViewById(R.id.notesRecyclerView)

            //gestionnaire de disposition linéaire pour organiser les éléments du RecyclerView
            val layoutManager = LinearLayoutManager(this)
            notesRecyclerView.layoutManager = layoutManager

            //adaptateur pour le RecyclerView en passant la liste des notes
            val adapter = NotesAdapter(notesList)

            //adaptateur pour le RecyclerView
            notesRecyclerView.adapter = adapter
            resultTextView.setVisibility(View.VISIBLE)
            resultTextView.setText("Résultat : Admis")
        })
    }
}
