package com.example.notes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.database.Note
import com.example.notes.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

// The data contained in the repository is fetched by the viewModel and later passed to the UI layer

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val repository: NotesRepository
) : ViewModel() {


    val allNotes: LiveData<List<Note>> = repository.getAllNotes()

    fun insertNote(note: Note) = viewModelScope.launch (Dispatchers.IO){ repository.insertNote(note = note) }

    fun deleteNote(note: Note) = viewModelScope.launch (Dispatchers.IO){ repository.deleteNote(note = note) }

    fun updateNote(note: Note) = viewModelScope.launch (Dispatchers.IO){ repository.updateNote(note = note) }
}