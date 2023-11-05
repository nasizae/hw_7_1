package com.example.myapplication.data.room

import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.data.model.NoteModel

@Dao
interface Dao {

    @Query("SELECT * FROM notes")
    fun getNotes():List<NoteModel>
}