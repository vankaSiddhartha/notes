package com.app.notes.repository

import android.content.Context
import com.app.notes.database.NotesDatabase
import com.app.notes.model.NotesModel

class NotesRepository(context: Context) {
    private val database = NotesDatabase(context)

    fun insertNote(note: NotesModel): Long {
        return database.insertNote(note)
    }

    fun getAllNotes(): List<NotesModel> {
        return database.getAllNotes()
    }

    fun updateNote(note: NotesModel): Int {
        return database.updateNote(note)
    }

    fun deleteNote(noteId: Int): Int {
        return database.deleteNote(noteId)
    }
}