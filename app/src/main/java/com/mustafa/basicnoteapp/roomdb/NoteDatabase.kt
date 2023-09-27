package com.mustafa.basicnoteapp.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Note::class] , version = 1 , exportSchema = false)
abstract class NoteDatabase  : RoomDatabase(){
    abstract fun noteDao() : NoteDao
}