package com.example.shop.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.shop.models.User

class UserDbHelper(val dbHelper: CommonDbHelper) {
    companion object {
        const val TABLE_NAME = "users"
        const val COLUMN_ID = "id"
        const val COLUMN_LOGIN = "login"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PHONE = "phone"
        const val COLUMN_PASSWORD = "password"

        fun onCreate(db: SQLiteDatabase?) {
            val query = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INT PRIMARY KEY, 
                $COLUMN_LOGIN TEXT,
                $COLUMN_EMAIL TEXT,
                $COLUMN_PHONE TEXT,
                $COLUMN_PASSWORD TEXT
            )
        """.trimIndent()
            db!!.execSQL(query)
        }

        fun onUpgrade(
            db: SQLiteDatabase?
        ) {
            db!!.execSQL("DROP TABLE IF EXISTS users")

            onCreate(db)
        }
    }


    fun getAllUsers(): ArrayList<User> {
        val users = mutableListOf<User>()
        val db = dbHelper.readableDatabase

        val cursor = db.query(
            TABLE_NAME, null, null, null, null, null, null
        )

        cursor.use { c ->
            val loginIndex = c.getColumnIndex(COLUMN_LOGIN)
            val emailIndex = c.getColumnIndex(COLUMN_EMAIL)
            val passwordIndex = c.getColumnIndex(COLUMN_PASSWORD)
            val phoneIndex = c.getColumnIndex(COLUMN_PHONE)

            while (c.moveToNext()) {
                val user = User(
                    c.getString(loginIndex),
                    c.getString(emailIndex),
                    c.getString(passwordIndex),
                    c.getString(phoneIndex),
                )
                users.add(user)
            }
        }

        return users as ArrayList<User>
    }

    fun addUser(user: User) {
        val content = ContentValues()
        content.put(COLUMN_LOGIN, user.login)
        content.put(COLUMN_EMAIL, user.email)
        content.put(COLUMN_PHONE, user.phone)
        content.put(COLUMN_PASSWORD, user.password)

        val db = dbHelper.writableDatabase
        db.insert(TABLE_NAME, null, content)
    }

    fun isUserExist(login: String, password: String): Boolean {
        val db = dbHelper.readableDatabase
        val selection = "$COLUMN_LOGIN = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(login, password)

        val query = db.query(
            TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null,
        )
        val result = query.moveToFirst()

        query.close()

        return result
    }

    fun deleteUser(login: String) {
        val db = dbHelper.writableDatabase

        db.delete(TABLE_NAME, "$COLUMN_LOGIN = ?", arrayOf(login))
    }

    fun isTableEmpty(): Boolean {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM $TABLE_NAME", null)
        cursor.moveToFirst()
        val count = cursor.getInt(0)
        cursor.close()
        return count == 0
    }
}