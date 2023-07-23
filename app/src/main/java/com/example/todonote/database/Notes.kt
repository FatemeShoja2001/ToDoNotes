package com.example.todonote.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo


@Entity(tableName="notes")
 data class Notes(

 @PrimaryKey(autoGenerate = true)
    val id:Int=0,

 @ColumnInfo(name="title")
    val title:String,

 @ColumnInfo(name="description")
    val description:String,


 @ColumnInfo(name="time")
 val time:String

 )
