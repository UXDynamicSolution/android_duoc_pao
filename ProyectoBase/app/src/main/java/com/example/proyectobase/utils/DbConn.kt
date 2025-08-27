package com.example.proyectobase.utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// Modelo simple de usuario
data class User(val id: Long? = null, val name: String, val email: String)

class DbConn(context: Context) : SQLiteOpenHelper(
    context,
    DB_NAME,
    null,
    DB_VERSION
) {
    override fun onCreate(db: SQLiteDatabase) {
        val createUsers = """
            CREATE TABLE $TABLE_USERS (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_NAME TEXT NOT NULL,
                $COL_EMAIL TEXT NOT NULL UNIQUE
            )
        """.trimIndent()
        db.execSQL(createUsers)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    // ---------- INSERT ----------
    fun insertUser(user: User): Long {
        val values = ContentValues().apply {
            put(COL_NAME, user.name)
            put(COL_EMAIL, user.email)
        }
        return writableDatabase.insert(TABLE_USERS, null, values)
    }

    // ---------- UPDATE ----------
    fun updateUser(id: Long, name: String, email: String): Int {
        val values = ContentValues().apply {
            put(COL_NAME, name)
            put(COL_EMAIL, email)
        }
        return writableDatabase.update(
            TABLE_USERS,
            values,
            "$COL_ID = ?",
            arrayOf(id.toString())
        )
    }

    // ---------- DELETE ----------
    fun deleteUser(id: Long): Int {
        return writableDatabase.delete(
            TABLE_USERS,
            "$COL_ID = ?",
            arrayOf(id.toString())
        )
    }

    // ---------- SELECT uno ----------
    fun getUserById(id: Long): User? {
        val cursor = readableDatabase.query(
            TABLE_USERS,
            arrayOf(COL_ID, COL_NAME, COL_EMAIL),
            "$COL_ID = ?",
            arrayOf(id.toString()),
            null, null, null
        )
        cursor.use { c ->
            return if (c.moveToFirst()) {
                c.toUser()
            } else null
        }
    }

    // ---------- SELECT todos ----------
    fun getAllUsers(): List<User> {
        val list = mutableListOf<User>()
        val cursor: Cursor = readableDatabase.rawQuery(
            "SELECT $COL_ID, $COL_NAME, $COL_EMAIL FROM $TABLE_USERS ORDER BY $COL_ID DESC",
            null
        )
        cursor.use { c ->
            while (c.moveToNext()) list.add(c.toUser())
        }
        return list
    }

    private fun Cursor.toUser(): User =
        User(
            id = getLong(getColumnIndexOrThrow(COL_ID)),
            name = getString(getColumnIndexOrThrow(COL_NAME)),
            email = getString(getColumnIndexOrThrow(COL_EMAIL))
        )

    companion object {
        private const val DB_NAME = "app_local.db"
        private const val DB_VERSION = 1

        private const val TABLE_USERS = "users"
        private const val COL_ID = "id"
        private const val COL_NAME = "name"
        private const val COL_EMAIL = "email"
    }
}
