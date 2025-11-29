package com.example.shop.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.shop.models.Item
import com.example.shop.models.User

class CommonDbHelper private  constructor (context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "app", factory, 1) {

    companion object {
        @Volatile
        private var instance: CommonDbHelper? = null

        fun getInstance(context: Context): CommonDbHelper {
            return instance ?: synchronized(this) {
                instance ?: CommonDbHelper(context.applicationContext, null).also { instance = it }
            }
        }
    }

    public val userDbHelper = UserDbHelper(this)
    public val productsDbHelper = ProductsDbHelper(this)
    public val selectedProductsDbHelper = SelectedProductsDbHelper(this)

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("PRAGMA foreign_keys = ON")

        UserDbHelper.onCreate(db)
        ProductsDbHelper.onCreate(db)
        SelectedProductsDbHelper.onCreate(db)
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        UserDbHelper.onUpgrade(db)
        SelectedProductsDbHelper.onUpgrade(db)
        ProductsDbHelper.onUpgrade(db)
    }

    fun initializeDb(){
        if (userDbHelper.isTableEmpty())
            userDbHelper.addUser(User("a", "a", "a", "a"))

        if (productsDbHelper.isTableEmpty()){
            productsDbHelper.addItem(Item(1, "i1", "t1", "d1", "text1", 100))
            productsDbHelper.addItem(Item(2, "i2", "t2", "d2", "text2", 200))
            productsDbHelper.addItem(Item(3, "i3", "t3", "d3", "text3", 300))
        }
    }
}