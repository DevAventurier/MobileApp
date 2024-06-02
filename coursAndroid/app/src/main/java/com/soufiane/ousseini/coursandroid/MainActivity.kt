package com.soufiane.ousseini.coursandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val btnConnect = findViewById<Button>(R.id.connect)

        val email1 = findViewById<EditText>(R.id.email)
        val mot1  = findViewById<EditText>(R.id.mot)
        btnConnect.setOnClickListener({view: View ->
            val email = email1.text.toString()
            val mot  = mot1.text.toString()
            if (email == "soufiane@gmail.com" && mot == "1234"){
                val myIntent :Intent=Intent(this,HomeActivity::class.java)
                startActivity(myIntent)
            }else{
                val myIntent :Intent=Intent(this,MainActivity::class.java)
                startActivity(myIntent)
            }
        })
    }
}