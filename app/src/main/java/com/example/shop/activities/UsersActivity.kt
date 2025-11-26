package com.example.shop.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.models.User
import com.example.shop.R
import com.example.shop.adapters.UsersAdapter
import com.example.shop.database.UserDbHelper

class UsersActivity : AppCompatActivity() {
    private lateinit var productsLink: TextView
    private lateinit var usersList: RecyclerView
    private lateinit var users: ArrayList<User>
    private lateinit var dbHelper: UserDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        initialize()

        productsLink.setOnClickListener {
            val intent = Intent(this, ItemsActivity::class.java)
            startActivity(intent)
        }
    }

    fun initialize() {
        dbHelper = UserDbHelper(this, null)
        productsLink = findViewById(R.id.products_link)
        usersList = findViewById(R.id.items_list)
        users = dbHelper.getAllUsers()

        usersList.layoutManager = LinearLayoutManager(this)
        usersList.adapter = UsersAdapter(users)
    }
}