package com.example.shop.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.models.Item
import com.example.shop.adapters.ItemAdapter
import com.example.shop.R
import com.example.shop.database.CommonDbHelper

class ItemsActivity : AppCompatActivity() {
    private lateinit var usersLink: TextView
    private lateinit var cartLink: TextView
    private lateinit var itemsList: RecyclerView
    private lateinit var items: ArrayList<Item>
    private var dbHelper: CommonDbHelper = CommonDbHelper.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        initialize()

        usersLink.setOnClickListener {
            val intent = Intent(this, UsersActivity::class.java)
            startActivity(intent)
        }

        cartLink.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }

    fun initialize() {
        usersLink = findViewById(R.id.users_link)
        cartLink = findViewById(R.id.cart_link)
        itemsList = findViewById<RecyclerView>(R.id.items_list)
        items = dbHelper.productsDbHelper.getAllItems()

        itemsList.layoutManager = LinearLayoutManager(this)
        itemsList.adapter = ItemAdapter(items, this)
    }
}