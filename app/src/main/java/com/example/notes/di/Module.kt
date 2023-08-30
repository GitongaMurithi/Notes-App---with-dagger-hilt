package com.example.notes.di

import android.app.Application
import androidx.room.Room
import com.example.notes.database.NoteDatabase
import com.example.notes.repository.NoteRepositoryImpl
import com.example.notes.repository.NotesRepository
import com.example.notes.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Dagger hilt requires this module in order to know how to create certain dependencies
// and how to pass them to the requiring objects and classes

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun providesDatabase(application: Application): NoteDatabase {
        return Room.databaseBuilder(application, NoteDatabase::class.java, name = DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun providesRepository(noteDatabase: NoteDatabase) : NotesRepository {
        return NoteRepositoryImpl(noteDatabase.dao)
    }
}