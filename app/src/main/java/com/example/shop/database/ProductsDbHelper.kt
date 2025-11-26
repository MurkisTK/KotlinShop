package com.example.shop.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.shop.models.Item

class ProductsDbHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "app", factory, 1) {
    companion object {
        const val TABLE_NAME = "products"
        const val COLUMN_ID = "id"
        const val COLUMN_PRICE = "price"
        const val COLUMN_IMAGE = "image"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_TEXT = "text"
        const val COLUMN_TITLE = "title"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INT PRIMARY KEY, 
                $COLUMN_IMAGE TEXT,
                $COLUMN_TITLE TEXT, 
                $COLUMN_DESCRIPTION TEXT, 
                $COLUMN_TEXT TEXT, 
                $COLUMN_PRICE INT
            )
        """.trimIndent()

        db!!.execSQL(query)
        addItem(Item(1, "i1", "t1", "d1", "text1", 100))
        addItem(Item(2, "i2", "t2", "d2", "text2", 200))
        addItem(Item(3, "i3", "t3", "d3", "text3", 300))
    }

    override fun onUpgrade(
        db: SQLiteDatabase?, oldVersion: Int, newVersion: Int
    ) {
        db!!.execSQL("DROP TABLE IF EXISTS products")

        onCreate(db)
    }

    fun getItemById(id: Int): Item? {
        val db = readableDatabase
        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(id.toString())

        val cursor = db.query(
            TABLE_NAME, null, selection, selectionArgs, null, null, null
        )
        cursor.use { c ->
            if (c.moveToFirst()) {
                val idIndex = c.getColumnIndex(COLUMN_ID)
                val textIndex = c.getColumnIndex(COLUMN_TEXT)
                val titleIndex = c.getColumnIndex(COLUMN_TITLE)
                val descIndex = c.getColumnIndex(COLUMN_DESCRIPTION)
                val priceIndex = c.getColumnIndex(COLUMN_PRICE)
                val imageIndex = c.getColumnIndex(COLUMN_IMAGE)

                return Item(
                    c.getInt(idIndex),
                    c.getString(imageIndex),
                    c.getString(titleIndex),
                    c.getString(descIndex),
                    c.getString(textIndex),
                    c.getInt(priceIndex),
                )
            } else {
                return null
            }
        }
    }

    fun getAllItems(): ArrayList<Item> {
        val items = mutableListOf<Item>()
        val db = readableDatabase

        val cursor = db.query(
            TABLE_NAME, null, null, null, null, null, null
        )

        cursor.use { c ->
            val idIndex = c.getColumnIndex(COLUMN_ID)
            val textIndex = c.getColumnIndex(COLUMN_TEXT)
            val titleIndex = c.getColumnIndex(COLUMN_TITLE)
            val descIndex = c.getColumnIndex(COLUMN_DESCRIPTION)
            val priceIndex = c.getColumnIndex(COLUMN_PRICE)
            val imageIndex = c.getColumnIndex(COLUMN_IMAGE)

            while (c.moveToNext()) {
                val item = Item(
                    c.getInt(idIndex),
                    c.getString(imageIndex),
                    c.getString(titleIndex),
                    c.getString(descIndex),
                    c.getString(textIndex),
                    c.getInt(priceIndex),
                )
                items.add(item)
            }
        }

        db.close()
        return items as ArrayList<Item>;
    }

    fun addItem(item: Item) {
        val content = ContentValues()
        content.put(COLUMN_TITLE, item.title)
        content.put(COLUMN_ID, item.id)
        content.put(COLUMN_PRICE, item.price)
        content.put(COLUMN_IMAGE, item.image)
        content.put(COLUMN_DESCRIPTION, item.desc)
        content.put(COLUMN_TEXT, item.text)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, content)

        db.close()
    }
}