package com.app.notes.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.notes.model.NotesModel
import com.app.notes.repository.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NotesRepository = NotesRepository(application)
    private val _notes = MutableLiveData<List<NotesModel>>()
    val notes: LiveData<List<NotesModel>> = _notes

    init {
        refreshNotes()
    }

    fun insertNote(note: NotesModel) {
        repository.insertNote(note)
        refreshNotes()
    }

    fun refreshNotes() {
        _notes.value = repository.getAllNotes()
    }

    fun updateNote(note: NotesModel) {
        repository.updateNote(note)
        refreshNotes()
    }

    fun deleteNote(noteId: Int) {
        repository.deleteNote(noteId)
        refreshNotes()
    }
}