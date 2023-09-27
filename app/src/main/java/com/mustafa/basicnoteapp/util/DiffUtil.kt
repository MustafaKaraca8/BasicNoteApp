package com.mustafa.basicnoteapp.util

import androidx.recyclerview.widget.DiffUtil
import com.mustafa.basicnoteapp.roomdb.Note

object DiffUtilSingleton {
    val instance = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }
}