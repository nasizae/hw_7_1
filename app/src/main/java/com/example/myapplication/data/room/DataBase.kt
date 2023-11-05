package com.example.myapplication.data.room

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [Note::class], version = 1)
abstract class DataBase:RoomDatabase() {
    abstract fun getDao():Dao
}