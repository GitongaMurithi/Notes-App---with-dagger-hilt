package com.example.notes.repository

import androidx.lifecycle.LiveData
import com.example.notes.database.Note

// Simulates the dao. This repository can later be used in the viewModel

interface NotesRepository {

    fun getAllNotes() : LiveData<List<Note>>

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun updateNote(note: Note)
}