package com.yerimadevoir.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)


        val listView = findViewById<ListView>(R.id.listView)

        val personnes = listOf(
            Personne("MR", "Yves", 29, R.drawable.deux),
            Personne("YERIMA", "Hatimou", 23, R.drawable.trois),
            Personne("ASSEMA", "Rachad", 23, R.drawable.quatre),
            Personne("ANDROID", "Studio", 50, R.drawable.cinq)
        )

        val adapter = PersonneAdapter(this, personnes)
        listView.adapter = adapter
    }
}