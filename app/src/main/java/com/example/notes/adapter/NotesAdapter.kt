package com.example.notes.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.database.Note
import kotlin.random.Random

class NotesAdapter (private val context: Context, private val listener: OnNoteClickedListener) : RecyclerView.Adapter<NotesAdapter.NotesVieHolder>() {

    private val note = ArrayList<Note>()
    private val notes = ArrayList<Note>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesVieHolder {
        return NotesVieHolder(
            LayoutInflater.from(context).inflate(R.layout.note_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return note.size
    }

    override fun onBindViewHolder(holder: NotesVieHolder, position: Int) {
        val currentNote = note[position]
        holder.title.text = currentNote.title
        holder.body.text = currentNote.body
        holder.date.text = currentNote.date
        holder.title.isSelected  = true
        holder.date.isSelected = true

        holder.cardView.setCardBackgroundColor(holder.itemView.resources.getColor(randomColorGenerator(),null))

        holder.cardView.setOnClickListener {
            listener.onClick(note[holder.adapterPosition])
        }

        holder.cardView.setOnLongClickListener {
            listener.onLongPress(note[holder.adapterPosition] , holder.cardView)
            true
        }
    }

    inner class NotesVieHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.notesCardView)
        val title: TextView = itemView.findViewById(R.id.title)
        val body: TextView = itemView.findViewById(R.id.body)
        val date: TextView = itemView.findViewById(R.id.date)
    }

    private fun randomColorGenerator() : Int {
        val color = ArrayList<Int>()
        color.add(R.color.Amethyst)
        color.add(R.color.Artichoke_green)
        color.add(R.color.Aqua)
        color.add(R.color.Blue)
        color.add(R.color.Barbie_Pink)
        color.add(R.color.Cadmium_green)
        color.add(R.color.Coquelicot)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(color.size)
        return color[randomIndex]
    }

    interface OnNoteClickedListener {
        fun onClick(note: Note)
        fun onLongPress(note: Note, cardView: CardView)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList : List<Note>) {
        notes.clear()
        notes.addAll(newList)

        note.clear()
        note.addAll(notes)

        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun searchNote(query : String) {
        note.clear()

        for (item in notes) {
            if (item.title?.contains(query) == true || item.body?.contains(query) == true) {
                note.add(item)
            }
        }
        notifyDataSetChanged()
    }
}