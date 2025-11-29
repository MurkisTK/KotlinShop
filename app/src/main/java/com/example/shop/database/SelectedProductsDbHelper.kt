package com.example.shop.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.shop.database.UserDbHelper.Companion.COLUMN_LOGIN
import com.example.shop.models.SelectedProduct

class SelectedProductsDbHelper(val dbHelper: CommonDbHelper) {
    companion object {
        const val TABLE_NAME = "selected_products"
        const val COLUMN_ID = "id"
        const val COLUMN_PRODUCT_ID = "product_id"
        const val COLUMN_COUNT = "count"

        fun onCreate(db: SQLiteDatabase?) {
            val query = """
                CREATE TABLE $TABLE_NAME (
                    $COLUMN_ID INT PRIMARY KEY, 
                    $COLUMN_PRODUCT_ID INT, 
                    $COLUMN_COUNT INT,
                    FOREIGN KEY ($COLUMN_PRODUCT_ID) REFERENCES ${ProductsDbHelper.TABLE_NAME}(${ProductsDbHelper.COLUMN_ID})
                )
            """.trimIndent()
            db!!.execSQL(query)
        }

        fun onUpgrade(
            db: SQLiteDatabase?,
        ) {
            db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")

            onCreate(db)
        }
    }

    fun getAllSelectedProducts(): ArrayList<SelectedProduct>{
        val selectedProducts = mutableListOf<SelectedProduct>()
        val db = dbHelper.readableDatabase

        val cursor = db.query(TABLE_NAME, null, null, null, null, null, null)

        cursor.use { c ->
            val productIdIndex = c.getColumnIndex(COLUMN_PRODUCT_ID)
            val countIndex = c.getColumnIndex(COLUMN_COUNT)

            while (c.moveToNext()) {
                val selectedProduct = SelectedProduct(
                    c.getInt(productIdIndex),
                    c.getInt(countIndex),
                )
                selectedProducts.add(selectedProduct)
            }
        }

        return selectedProducts as ArrayList<SelectedProduct>
    }

    fun updateSelectedProducts(productId: Int, count: Int){
        val db = dbHelper.writableDatabase

        if (count < 1) {
            db.delete(TABLE_NAME, "$COLUMN_PRODUCT_ID = ?", arrayOf(productId.toString()))
        }
        else {
            val content = ContentValues().apply {
                put(COLUMN_COUNT, count)
            }

            db.update(TABLE_NAME, content, "$COLUMN_PRODUCT_ID = ?", arrayOf(productId.toString()))
        }
    }

    fun addNewSelectedProduct(id: Int) {
        if (isSelectedProductExist(id))
            return

        val content = ContentValues()
        content.put(COLUMN_PRODUCT_ID, id)
        content.put(COLUMN_COUNT, 1)
        val db = dbHelper.writableDatabase
        db.insert(TABLE_NAME, null, content)
    }

    fun isSelectedProductExist(id: Int): Boolean {
        val db = dbHelper.readableDatabase
        val selection = "$COLUMN_PRODUCT_ID = ?"
        val selectionArgs = arrayOf(id.toString())

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

    fun deleteProducts() {
        val db = dbHelper.writableDatabase

        db.delete(TABLE_NAME, null, null)
    }
}