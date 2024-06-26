package com.yerima.application

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.ImageView

class PostAdapter (var mContext: Context, var resource: Int, var values: ArrayList<Post>):
    ArrayAdapter<Post>(mContext, resource, values) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val post=values[position]
        val itemView=LayoutInflater.from(mContext).inflate(resource, parent, false)
        val tvTitre=itemView.findViewById<TextView>(R.id.tvTitre)
        val tvDescription=itemView.findViewById<TextView>(R.id.tvDescription)
        val imagePost=itemView.findViewById<ImageView>(R.id.imageView)

        tvTitre.text = post.titre
        tvDescription.text = post.description
        imagePost.setImageResource(post.image)

        return itemView

    }
    }