package com.yerimadevoir.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class PersonneAdapter(private val context: Context, private val personnes: List<Personne>) : BaseAdapter() {

    override fun getCount(): Int {
        return personnes.size
    }

    override fun getItem(position: Int): Any {
        return personnes[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_personne, parent, false)

        val personne = getItem(position) as Personne

        val nomTextView: TextView = view.findViewById(R.id.nomTextView)
        val prenomTextView: TextView = view.findViewById(R.id.prenomTextView)
        val ageTextView: TextView = view.findViewById(R.id.ageTextView)
        val photoImageView: ImageView = view.findViewById(R.id.photoImageView)

        nomTextView.text = personne.nom
        prenomTextView.text = personne.prenom
        ageTextView.text = personne.age.toString()
        photoImageView.setImageResource(personne.photo)

        return view
    }
}
