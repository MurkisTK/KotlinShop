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

class ItemsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        val usersLink: TextView = findViewById(R.id.users_link)
        val itemsList = findViewById<RecyclerView>(R.id.items_list)
        val items = arrayListOf<Item>()

        items.add(Item(1, "i1", "t1", "d1", "text1", 100))
        items.add(Item(2, "i2", "t2", "d2", "text2", 200))
        items.add(Item(3, "i3", "t3", "d3", "text3", 300))

        itemsList.layoutManager = LinearLayoutManager(this)
        itemsList.adapter = ItemAdapter(items, this)

        usersLink.setOnClickListener {
            val intent = Intent(this, UsersActivity::class.java)
            startActivity(intent)
        }
    }
}