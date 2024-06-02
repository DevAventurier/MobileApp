package com.yerima.vendredi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class Un : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_un)

        val btnConnect=findViewById<Button>(R.id.button)

        btnConnect.setOnClickListener({view: View ->
            val myIntent : Intent = Intent(this, deux::class.java)
            startActivity(myIntent)
        })
    }


}