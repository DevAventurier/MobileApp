package com.soufiane.ousseini.coursandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.soufiane.ousseini.coursandroid.data.Post
import com.soufiane.ousseini.coursandroid.db.Database

class HomeActivity : AppCompatActivity() {

    lateinit var listPosts: ListView
    var postArray = ArrayList<Post>()
    lateinit var adapterPosts: PostAdapter

    lateinit var db: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        listPosts = findViewById(R.id.list_posts)
        db = Database(this)
        postArray = db.findPosts()
        adapterPosts = PostAdapter(this, R.layout.item_post, postArray)


        val listPosts = findViewById<ListView>(R.id.list_posts)

//        val postArray = arrayListOf(
//            Post("Livre 1", "Voici la description d'un bon livre de la fable de la fontaine",R.drawable.image1),
//            Post("Livre 2", "Voici la description d'un bon livre de la fable de la fontaine",R.drawable.image2),
//            Post("Livre 3", "Voici la description d'un bon livre de la fable de la fontaine",R.drawable.image3),
//            Post("Livre 4", "Voici la description d'un bon livre de la fable de la fontaine",R.drawable.image4),
//            Post("Livre 5", "Voici la description d'un bon livre de la fable de la fontaine",R.drawable.image5),
//        )
//
//        val adapter = PostAdapter(this,R.layout.item_post,postArray)
//
//        listPosts.adapter = adapter
//        registerForContextMenu(listPosts)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.itemAdd -> {
                Toast.makeText(this,"Add Post",Toast.LENGTH_SHORT).show()
                Intent(this,AddPostActivity::class.java).also {
                    startActivity(it)
                }

            }
            R.id.itemConfig -> {
                Toast.makeText(this,"Config App",Toast.LENGTH_SHORT).show()
            }
            R.id.itemDeconnect -> {
                //finish()
                showLogoutConfirmDialog()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.context_menu,menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info:AdapterView.AdapterContextMenuInfo = item.menuInfo as AdapterContextMenuInfo
        val position: Int = info.position
        when(item.itemId){
            R.id.afficher ->{
                Toast.makeText(this,"Show detail ${position}",Toast.LENGTH_SHORT).show()
            }
            R.id.supprimer ->{
                Toast.makeText(this," Suppression ${position}",Toast.LENGTH_SHORT).show()
            }
        }
        return super.onContextItemSelected(item)
    }

    fun showLogoutConfirmDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmation")
        builder.setMessage("Etes-vous sure de vouloir quitter ???")
        builder.setPositiveButton("Oui"){dialogInterface, id ->
            finish()
        }
        builder.setNegativeButton("Non"){dialogInterface,id ->
            dialogInterface.dismiss()
        }
        builder.setNeutralButton("Annuler"){dialogInterface,id ->
            dialogInterface.dismiss()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()

    }
}