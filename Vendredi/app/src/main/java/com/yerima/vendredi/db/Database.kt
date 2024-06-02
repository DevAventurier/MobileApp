package com.yerima.vendredi.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.yerima.vendredi.data.Post

class Database(mContext: Context):SQLiteOpenHelper(mContext, "DB_NAME", null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        //val nom du srtring et 3 dble cot pr pvoir entrer plusieurs elements
        val createTablePosts = """
            CREATE TABLE $POSTS_TABLE_NAME(
                $POST_ID integer PRIMARY KEY,
                $TITRE varchar(50),
                $DESCRIPTION text,
                $IMAGE blob
            )
        """.trimIndent()


        db?.execSQL(createTablePosts) //POUR EXECUTER LA TABLE KON VIEN DE CREER
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $POSTS_TABLE_NAME")
        onCreate(db)
    }

    //fun findPosts():ArrayList<Post>{}

    fun addPost(post: Post): Boolean{
        //inserer un nouveau post dans la base de donnees
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TITRE, post.titre)
        values.put(DESCRIPTION, post.description)
        values.put(IMAGE, post.image)

        val result = db.insert(POSTS_TABLE_NAME, null, values).toInt()
        db.close()

        return result != -1
    }

//    fun updatePost(post: Post): Boolean{
//        //inserer un nouveau post dans la base de donnees
//        val db = this.writableDatabase
//        val values = ContentValues()
//        values.put(TITRE, post.titre)
//        values.put(DESCRIPTION, post.description)
//        values.put(IMAGE, post.image)
//
//        val result = db.insert(POSTS_TABLE_NAME, null, values).toInt()
//        db.close()
//
//        return result != -1
//    }

    companion object{
        private  val DB_NAME = "database_tp"

        private   val DB_VERSION = 1

        //POSTS
        private  val POSTS_TABLE_NAME = "posts"
        private val POST_ID = "id"
        private val TITRE = "titre"
        private val DESCRIPTION = "description"
        private val IMAGE = "image"

        //USERS
//        private  val USERS_TABLE_NAME = "users"
//        private val USERS_ID = "id"
//        private val TITRE = "titre"
//        private val DESCRIPTION = "description"
//        private val IMAGE = "image"
    }
}

// 1 = changement effectuer au nivo de la class post kon avait crer avant [package data]