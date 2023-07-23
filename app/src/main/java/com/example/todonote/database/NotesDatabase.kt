package com.example.todonote.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Notes::class], version = 1)
abstract class NotesDatabase /*name of Database*/ : RoomDatabase(){


    abstract fun getNotesDao():NotesDao  //name of Dao
    
    companion object{
        fun buildDatabase(context: Context)=
            Room.databaseBuilder(context.applicationContext,NotesDatabase::class.java,"notes.db")
            .allowMainThreadQueries().build()
    }

}