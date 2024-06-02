package com.yerimadevoir.myapplication

import android.R
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yerimadevoir.myapplication.database.DatabaseHelper


class DashboardEnseignantActivity : AppCompatActivity() {
    private var notesEditText: EditText? = null
    private var saveButton: Button? = null
    private var databaseHelper: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_list_item)
        setContentView(R.layout.activity_dashboard_enseignant)
        notesEditText = findViewById<EditText>(R.id.notesEditText)
        saveButton = findViewById<Button>(R.id.saveButton)
        databaseHelper = DatabaseHelper(this)
        saveButton.setOnClickListener(View.OnClickListener {
            val notes = notesEditText.getText().toString()
            databaseHelper!!.addNote(notes)
            Toast.makeText(this@DashboardEnseignantActivity, "Note saved!", Toast.LENGTH_SHORT).show()
        })
    }


}