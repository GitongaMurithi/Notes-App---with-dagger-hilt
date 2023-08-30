package com.example.notes.repository

import androidx.lifecycle.LiveData
import com.example.notes.database.NotesDao
import com.example.notes.database.Note

class NoteRepositoryImpl (private val dao : NotesDao) : NotesRepository {

    override fun getAllNotes(): LiveData<List<Note>> {
        return dao.getAllNotes()
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note = note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note = note)
    }

    override suspend fun updateNote(note: Note) {
        dao.updateNote(title = note.title , id = note.id , body = note.body)
    }

}