package com.app.notes.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.app.notes.model.NotesModel

class NotesDatabase(context: Context):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "notes_database"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "notes"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NOTE = "note"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_TIME = "time"
    }

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

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    fun insertNote(note: NotesModel): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOTE, note.note)
            put(COLUMN_TITLE, note.title)
            put(COLUMN_TIME, note.time)
        }
        return db.insert(TABLE_NAME, null, values)
    }

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

    fun updateNote(note: NotesModel): Int {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOTE, note.note)
            put(COLUMN_TITLE, note.title)
            put(COLUMN_TIME, note.time)
        }
        return db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(note.id.toString()))
    }

    fun deleteNote(noteId: Int): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(noteId.toString()))
    }
}