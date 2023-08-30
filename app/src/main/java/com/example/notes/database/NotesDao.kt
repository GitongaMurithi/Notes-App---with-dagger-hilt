package com.example.notes.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// Basically this dao (Data Access Object) interface defines
// how to access the database by defining functions to perform those tasks
// Tasks that runs in other threads (not in the MAIN thread) should be suspend functions

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("Update notes set title = :title, body = :body where id = :id")
    suspend fun updateNote(title : String?, id : Int?, body : String?)

    @Query("select * from notes order by id asc")
    fun getAllNotes() : LiveData<List<Note>>
}