package com.mustafa.basicnoteapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafa.basicnoteapp.roomdb.Note
import com.mustafa.basicnoteapp.roomdb.NoteDatabase
import com.mustafa.basicnoteapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel
@Inject
constructor(private val noteDatabase: NoteDatabase): ViewModel() {

    val notes = noteDatabase.noteDao().getAllNote()

    private var insertNoteMsg = MutableLiveData<Resource<Note>>()
    val insertNoteMassage : LiveData<Resource<Note>>
        get() = insertNoteMsg

   fun resetInsertNoteMsg(){
        insertNoteMsg = MutableLiveData<Resource<Note>>()
   }

    fun insertNote(note : Note) = viewModelScope.launch {
        noteDatabase.noteDao().insertNote(note)
    }

    fun updateNote(note : Note) = viewModelScope.launch {
        noteDatabase.noteDao().updateNote(note)
    }

    fun getNoteWithId(noteId : Int) = viewModelScope.launch{
        noteDatabase.noteDao().getNote(noteId)
    }

    fun makeNote (title : String , note : String ){

        if(title.isEmpty() || note.isEmpty()){
            insertNoteMsg.postValue(Resource.error("Enter Title and Text" , null))
            return
        }

        val mkNote = Note(title , note )
        insertNote(mkNote)
        insertNoteMsg.postValue(Resource.success(mkNote))
    }

    fun updateOldNote(title: String, note: String , noteUuid : Int) {
        viewModelScope.launch {
            val existingNote = noteDatabase.noteDao().getNote(noteUuid)
            if (title.isEmpty() || note.isEmpty()) {
                insertNoteMsg.postValue(Resource.error("Enter Title and Text" , null))
                return@launch
            }
            // Güncellemeleri yap
            existingNote.title = title
            existingNote.note = note
            updateNote(existingNote)
            insertNoteMsg.postValue(Resource.success(existingNote))
        }
    }

}