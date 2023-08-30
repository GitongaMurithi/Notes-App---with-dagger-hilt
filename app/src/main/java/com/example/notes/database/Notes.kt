package com.example.notes.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

// This class serves as the table in the database.
// In other terms, we define the columns of each table there is in the database here
// This table has four columns: Id, title, body and date

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)val id : Int?,
    @ColumnInfo(name = "title")val title : String?,
    @ColumnInfo(name = "body")val body : String?,
    @ColumnInfo(name = "date")val date : String?
) : Serializable
