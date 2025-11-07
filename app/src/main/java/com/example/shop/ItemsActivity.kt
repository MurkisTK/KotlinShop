package com.example.shop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        val itemsList = findViewById<RecyclerView>(R.id.items_list)
        val items = arrayListOf<Item>()

        items.add(Item(1, "i1", "t1", "d1", "text1", 100))
        items.add(Item(2, "i2", "t2", "d2", "text2", 200))
        items.add(Item(3, "i3", "t3", "d3", "text3", 300))

        itemsList.layoutManager = LinearLayoutManager(this)
        itemsList.adapter = ItemAdapter(items, this)
    }
}