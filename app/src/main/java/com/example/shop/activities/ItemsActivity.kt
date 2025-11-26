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
import com.example.shop.database.ProductsDbHelper
import org.w3c.dom.Text

class ItemsActivity : AppCompatActivity() {
    private lateinit var usersLink: TextView;
    private lateinit var itemsList: RecyclerView;
    private lateinit var items: ArrayList<Item>;
    private var dbHelper: ProductsDbHelper = ProductsDbHelper(this, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        initialize()

        usersLink.setOnClickListener {
            val intent = Intent(this, UsersActivity::class.java)
            startActivity(intent)
        }
    }

    fun initialize() {
        usersLink = findViewById(R.id.users_link)
        itemsList = findViewById<RecyclerView>(R.id.items_list)
        items = dbHelper.getAllItems()

        itemsList.layoutManager = LinearLayoutManager(this)
        itemsList.adapter = ItemAdapter(items, this)
    }
}