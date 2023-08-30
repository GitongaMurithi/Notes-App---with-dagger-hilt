package com.example.notes.database

import androidx.room.Database
import androidx.room.RoomDatabase

// This is the app's database that contains the table(s)

@Database(entities = [Note::class], version = 1 , exportSchema = false)
abstract class NoteDatabase : RoomDatabase(){
    abstract val dao:NotesDao
}