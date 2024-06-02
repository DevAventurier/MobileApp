package com.soufiane.ousseini.coursandroid.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.soufiane.ousseini.coursandroid.data.Post

class Database (mContext : Context) : SQLiteOpenHelper(mContext, DB_NAME,null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTablePosts = """
            CREATE TABLE $POSTS_TABLE_NAME(
            $POST_ID integer PRIMARY KEY,
            $TITRE varchar(50),
            $DESCRIPTION text,
            $IMAGE blob
            )
        """.trimIndent()
        db?.execSQL(createTablePosts)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $POSTS_TABLE_NAME")
        onCreate(db)
    }

    fun addPost(post: Post):Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TITRE, post.titre)
        values.put(DESCRIPTION, post.description)
        values.put(IMAGE, post.image)
        val result = db.insert(POSTS_TABLE_NAME, null,values).toInt()
        db.close()
        return  result != -1
    }


    companion object{
        private val DB_NAME = "database_tp"
        private  val DB_VERSION = 1

        //  POSTS
        private val POSTS_TABLE_NAME = "posts"
        private val POST_ID = "id"
        private val TITRE = "titre"
        private val DESCRIPTION = "description"
        private val IMAGE = "image"

    }

    fun findPosts(): ArrayList<Post> {
        val posts = ArrayList<Post>()
        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM $POSTS_TABLE_NAME"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()){
                do {
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow(POST_ID))
                    val title = cursor.getString(cursor.getColumnIndexOrThrow(TITRE))
                    val description = cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION))
                    val image = cursor.getBlob(cursor.getColumnIndexOrThrow(IMAGE))
                    val post = Post(id,title,description,image)
                    posts.add(post)
                } while (cursor.moveToNext())
            }
        }
        db.close()
        return posts
    }

    fun findPosts(_id:Int): Post{
        val post = Post(0,"","", ByteArray(0))
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $POSTS_TABLE_NAME WHERE $POST_ID=$_id"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
                cursor.moveToFirst()
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow(POST_ID))
                    val title = cursor.getString(cursor.getColumnIndexOrThrow(TITRE))
                    val description = cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION))
                    val image = cursor.getBlob(cursor.getColumnIndexOrThrow(IMAGE))
                }
        }
        cursor.close()
        return post
    }

    fun updatePost(post: Post): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TITRE, post.titre)
        values.put(DESCRIPTION, post.description)
        values.put(IMAGE, post.image)
        val result =
            db.update(POSTS_TABLE_NAME, values, POST_ID + "+?",
                arrayOf(post.id.toString())).toInt()
        db.close()
        return result != -1
    }
}