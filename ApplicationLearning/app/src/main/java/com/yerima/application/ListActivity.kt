package com.yerima.application

import android.app.AlertDialog
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

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val listPosts = findViewById<ListView>(R.id.list_posts)

        val postArray = arrayListOf(
            Post("Livre 1", "Voici la description du bon livre de la fable de la fontaine",
                R.drawable.un),

            Post("Livre 2", "Voici la description du bon livre de la fable de la fontaine",
                R.drawable.deux),

            Post("Livre 3", "Voici la description du bon livre de la fable de la fontaine",
                R.drawable.trois),

            Post("Livre 4",
                "Voici la description du bon livre de la fable de la fontaine",
                R.drawable.quatre),

            Post("Livre 5",
                "Voici la description du bon livre de la fable de la fontaine",
                R.drawable.cinq)
        )

        val adapter = PostAdapter(this, R.layout.item_post, postArray)

        listPosts.adapter = adapter

        //crer un ecouteur devenement sur les items

        listPosts.setOnItemClickListener { adapterView, view, position, id ->
            val clickedPost = postArray[position]
            Toast.makeText(this, "Position : $position", Toast.LENGTH_SHORT).show()
        }
        registerForContextMenu(listPosts)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menus,menu) //on a charger le menu sur la page grace au composant home menu crer
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.itemAdd -> {
                Toast.makeText(this, "Add post", Toast.LENGTH_SHORT).show()
            }

            R.id.itemConfig -> {
                Toast.makeText(this, "Config Appi", Toast.LENGTH_SHORT).show()
            }

            R.id.itemDeconect -> {
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
        menuInflater.inflate(R.menu.context_menus, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {

        val info: AdapterView.AdapterContextMenuInfo=item.menuInfo as AdapterContextMenuInfo
        val position: Int = info.position

        when(item.itemId){

            R.id.itemShow -> {
                Toast.makeText(this, "Show Details $position", Toast.LENGTH_SHORT).show()
            }

            R.id.itemDelete -> {
                Toast.makeText(this, "Delete Details $position", Toast.LENGTH_SHORT).show()
            }

        }
        return super.onContextItemSelected(item)
    }

    fun showLogoutConfirmDialog() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmation")
        builder.setMessage("Etes vous sur de vouloir quitter ?!")

        builder.setPositiveButton("Oui") {dialogInterface, id ->
            finish()
        }

        builder.setNegativeButton("Non") {dialogInterface, id ->
            dialogInterface.dismiss()
        }

        builder.setNegativeButton("Annuler") {dialogInterface, id ->
            dialogInterface.dismiss()
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

}