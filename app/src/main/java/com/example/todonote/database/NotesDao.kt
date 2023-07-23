package com.example.todonote.database

import androidx.room.*


@Dao
interface NotesDao {

    @Query("SELECT * FROM notes") //name of table
    fun getAll(): MutableList<Notes>  //name of DataClass where have the table

    @Insert
    fun insert(notes: Notes)

    @Delete
    fun delete(notes: Notes)

    @Update
    fun update(notes: Notes)


    @Query("DELETE  FROM notes WHERE time = :time")
    fun deleteWithTime(time: String)

    @Query("SELECT * FROM notes WHERE time = :time")
    fun getNotesWithTime(time: String): MutableList<Notes>



}