package com.mustafa.basicnoteapp.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "note")
data class Note(
    var title : String,
    var note : String,
    @PrimaryKey(autoGenerate = true)
    var uuid : Int ?= null
)
