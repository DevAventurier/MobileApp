package com.yerimadevoir.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private val notesList: List<String>) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notesList[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val noteTextView: TextView = itemView.findViewById(R.id.noteTextView)

        fun bind(note: String) {
            noteTextView.text = note
        }
    }
}