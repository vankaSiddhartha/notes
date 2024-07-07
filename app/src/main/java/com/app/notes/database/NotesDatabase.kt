package com.app.notes.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.app.notes.model.NotesModel
// SQLiteOpenHelper class for managing notes database

class NotesDatabase(context: Context):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    // Constants for database details
    companion object {
        private const val DATABASE_NAME = "notes_database"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "notes"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NOTE = "note"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_TIME = "time"
    }
    // Method called when the database is created for the first time
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NOTE TEXT,
                $COLUMN_TITLE TEXT,
                $COLUMN_TIME TEXT
            )
        """.trimIndent()
        db?.execSQL(createTableQuery)
    }
    // Method called when the database needs to be upgraded
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    // Method to insert a new note into the database
    fun insertNote(note: NotesModel): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOTE, note.note)
            put(COLUMN_TITLE, note.title)
            put(COLUMN_TIME, note.time)
        }
        return db.insert(TABLE_NAME, null, values)
    }
 //  method to get all notes
    fun getAllNotes(): List<NotesModel> {
        val notesList = mutableListOf<NotesModel>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_ID DESC"
        val cursor = db.rawQuery(query, null)

        cursor.use {
            while (it.moveToNext()) {
                val note = NotesModel(
                    id = it.getInt(it.getColumnIndexOrThrow(COLUMN_ID)),
                    note = it.getString(it.getColumnIndexOrThrow(COLUMN_NOTE)),
                    title = it.getString(it.getColumnIndexOrThrow(COLUMN_TITLE)),
                    time = it.getString(it.getColumnIndexOrThrow(COLUMN_TIME))
                )
                notesList.add(note)
            }
        }
        return notesList
    }
  // method to update notes
    fun updateNote(note: NotesModel): Int {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOTE, note.note)
            put(COLUMN_TITLE, note.title)
            put(COLUMN_TIME, note.time)
        }
        return db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(note.id.toString()))
    }
  // method to delete note
    fun deleteNote(noteId: Int): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(noteId.toString()))
    }
}