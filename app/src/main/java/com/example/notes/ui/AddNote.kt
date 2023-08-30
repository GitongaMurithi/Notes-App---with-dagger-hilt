package com.example.notes.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notes.databinding.ActivityAddNoteBinding
import com.example.notes.database.Note
import java.text.SimpleDateFormat
import java.util.Date

class AddNote : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var newNote: Note
    private lateinit var oldNote: Note
    private var isUpdate = false

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            oldNote = intent.getSerializableExtra("current_note") as Note
            binding.titleEdittext.setText(oldNote.title)
            binding.bodyEditText.setText(oldNote.body)
            isUpdate = true
        } catch (exception: Exception) {
            exception.printStackTrace()
        }

        binding.check.setOnClickListener {
            val newTitle = binding.titleEdittext.text.toString()
            val newBody = binding.bodyEditText.text.toString()

            if (newTitle.isNotEmpty() || newBody.isNotEmpty()) {
                val formatter = SimpleDateFormat("EEE, dd MMM yyy HH:mm a")

                if (isUpdate) {
                    newNote = Note(
                        id = oldNote.id,
                        title = newTitle,
                        body = newBody,
                        date = formatter.format(Date())
                    )
                } else {
                    newNote =
                        Note(id = null, title = newTitle, body = newBody, date = formatter.format(Date()))
                }
                val intent = Intent()
                intent.putExtra("new_note", newNote)
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(
                    this@AddNote,
                    "Fill any of the fields above",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
        }

        binding.backArrow.setOnClickListener {
            onBackPressed()
        }
    }
}