package com.example.shop.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.shop.models.Item

class ProductsDbHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "app", factory, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE products (id INT PRIMARY KEY, image TEXT, title TEXT, description TEXT, text TEXT, price INT)"
        db!!.execSQL(query)
        addItem(Item(1, "i1", "t1", "d1", "text1", 100))
        addItem(Item(2, "i2", "t2", "d2", "text2", 200))
        addItem(Item(3, "i3", "t3", "d3", "text3", 300))
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        db!!.execSQL("DROP TABLE IF EXISTS products")

        onCreate(db)
    }

    fun getItemById(id: Int){

    }

    fun getAllItems(): ArrayList<Item>{
        return arrayListOf()
    }

    fun addItem(item: Item) {
        val content = ContentValues()
        content.put("title", item.title)
        content.put("id", item.id)
        content.put("price", item.price)
        content.put("image", item.image)
        content.put("desc", item.desc)
        content.put("text", item.text)

        val db = this.writableDatabase
        db.insert("products", null, content)

        db.close()
    }
}