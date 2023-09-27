package com.mustafa.basicnoteapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafa.basicnoteapp.roomdb.Note
import com.mustafa.basicnoteapp.roomdb.NoteDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowNotesViewModel
    @Inject
    constructor(private val noteDatabase: NoteDatabase) : ViewModel() {

    val notes = noteDatabase.noteDao().getAllNote()


    fun deleteNote(note : Note) = viewModelScope.launch{
        noteDatabase.noteDao().deleteNote(note)
    }
}