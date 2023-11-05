package com.example.myapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteModel(
    @PrimaryKey (autoGenerate = true)
    val id :Long,
    val title :String,
    val description :String,
)
