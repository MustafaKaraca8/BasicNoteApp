package com.mustafa.basicnoteapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.mustafa.basicnoteapp.R
import com.mustafa.basicnoteapp.roomdb.Note
import com.mustafa.basicnoteapp.util.DiffUtilSingleton
import com.mustafa.basicnoteapp.view.ShowNotesFragmentDirections

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.NoteHolder>() {

    class NoteHolder(view : View) : RecyclerView.ViewHolder(view)

    private val recyclerListDiffer = AsyncListDiffer(this , DiffUtilSingleton.instance)
    var noteList : List<Note>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_row,parent , false)
        return NoteHolder(view)
    }

    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val title = holder.itemView.findViewById<TextView>(R.id.titleTextView)
        val mNote = holder.itemView.findViewById<TextView>(R.id.noteTextView)
        val notes = noteList[position]
        holder.itemView.apply {
            title.text = notes.title
            mNote.text = notes.note
        }

        holder.itemView.setOnClickListener  {
            val action = ShowNotesFragmentDirections.actionShowNotesFragmentToAddNoteFragment(notes.uuid!!)
            findNavController(it).navigate(action)
        }
    }
}