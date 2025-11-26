package com.example.shop.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SelectedProductsDbHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "selectedProducts", factory, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE selected_products (id INT PRIMARY KEY, product_id INT, count INT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        db!!.execSQL("DROP TABLE IF EXISTS selected_products")

        onCreate(db)
    }

    fun addSelectedProduct(id: Int) {
        if (isSelectedProductExist(id))
            return

        val content = ContentValues()
        content.put("product_id", id)
        content.put("count", 1)
        val db = this.writableDatabase
        db.insert("selected_products", null, content)
    }

    fun isSelectedProductExist(id: Int): Boolean {
        val db = this.readableDatabase

        val query = db.rawQuery(
            "SELECT * FROM selected_products WHERE product_id = $id",
            null
        )
        val result = query.moveToFirst()

        query.close()

        return result
    }
}