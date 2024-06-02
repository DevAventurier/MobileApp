package com.soufiane.ousseini.coursandroid

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.soufiane.ousseini.coursandroid.data.Post
import com.soufiane.ousseini.coursandroid.db.Database
import java.io.ByteArrayOutputStream


class AddPostActivity : AppCompatActivity() {
    lateinit var editTitre: EditText
    lateinit var editDescription: EditText
    lateinit var imgAlter: ImageView
    lateinit var btnPost: Button
    var isEditMode = false
    var bitmap: Bitmap? = null
    lateinit var db: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)

        editTitre = findViewById(R.id.editTitre)
        editDescription = findViewById(R.id.editDescription)//id o nivo du layout
        imgAlter = findViewById(R.id.imgAlter)
        btnPost = findViewById(R.id.btnPost)

        val mode = intent.getStringExtra("mode")
        val id = intent.getIntExtra("id", 0)
        val titre = intent.getStringExtra("titre")
        val description = intent.getStringExtra("description")
        val image = intent.getByteArrayExtra("image")

        db = Database(this)
        /* if(mode == "add"){
            isEditMode = false
        } else {
            isEditMode = true
            bitmap = image?.let {getBitmap(it)}
        }*/
        imgAlter.setImageBitmap(bitmap)


        val galeryLuncher = registerForActivityResult(ActivityResultContracts.GetContent()) { data ->
            val inputStream = contentResolver.openInputStream(data!!)
            bitmap = BitmapFactory.decodeStream(inputStream)
            imgAlter.setImageBitmap(bitmap)
            //imgAlter est le nom k jai donner a mon image
        }

        imgAlter.setOnClickListener {
            // ouvrir galerie
            galeryLuncher.launch("image/*")
        }

        btnPost.setOnClickListener {
            val titre = editTitre.text.toString()
            val description = editDescription.text.toString()
            if (titre.isEmpty() || description.isEmpty() || bitmap === null) {
                Toast.makeText(this, "Rempli tt les champs", Toast.LENGTH_LONG).show()
            } else {
                val imagesBLOB: ByteArray = getBytes(bitmap!!)
                val post = Post(titre, description, imagesBLOB)
                db.addPost(post)

                editTitre.setText("")
                editDescription.setText("")
                bitmap = null

                finish()
            }
        }




    }
    fun getBytes(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream)
        return stream.toByteArray()
    }

    fun getBitmap(byteArray: ByteArray): Bitmap {
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        return bitmap
    }
}






