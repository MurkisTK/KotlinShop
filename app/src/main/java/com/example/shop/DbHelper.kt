package com.example.shop

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "app", factory, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "CREATE TABLE users (id INT PRIMARY KEY, login TEXT, email TEXT, phone TEXT, password TEXT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        db!!.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun addUser(user: User) {
        val content = ContentValues()
        content.put("login", user.login)
        content.put("email", user.email)
        content.put("phone", user.phone)
        content.put("password", user.password)

        val db = this.writableDatabase
        db.insert("users", null, content)

        db.close()
    }
}