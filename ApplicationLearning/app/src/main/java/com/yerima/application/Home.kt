package com.yerima.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
//import android.widget.TextView
import android.content.Intent
import android.widget.EditText
import android.widget.Toast


class Home : AppCompatActivity() {

    val nom : String = "YERIMA"
    val password : String = "007"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnConnect=findViewById<Button>(R.id.connect)
        //val txtConnect=findViewById<TextView>(R.id.link)

        btnConnect.setOnClickListener({view: View ->

            val user = findViewById<EditText>(R.id.username)
            val pass = findViewById<EditText>(R.id.password)

            if (user.text.toString() == nom && pass.text.toString() == password) {
                val myIntent :Intent= Intent(this,loginSuccess::class.java)
                Toast.makeText(this, "Connection Reussie", Toast.LENGTH_LONG).show()
                startActivity(myIntent)
            } else {
                Toast.makeText(this, "Connection Echoue", Toast.LENGTH_LONG).show()
            }

        })
    }
}