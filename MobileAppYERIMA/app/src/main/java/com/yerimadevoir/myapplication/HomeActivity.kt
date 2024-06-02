package com.yerimadevoir.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnConnect3=findViewById<Button>(R.id.buttonGetSomme)

        btnConnect3.setOnClickListener({view: View ->
            val myIntent : Intent = Intent(this, SommeActivity::class.java)
            startActivity(myIntent)
        })
//pour aller vers la liste des personnes
        val btnConnect2=findViewById<Button>(R.id.buttonCheckList)

        btnConnect2.setOnClickListener({view: View ->
            val myIntent : Intent = Intent(this, ListActivity::class.java)
            startActivity(myIntent)
        })
    }
}
