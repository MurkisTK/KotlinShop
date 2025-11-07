package com.example.shop

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UsersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        val productsLink: TextView = findViewById(R.id.products_link)
        val usersList: RecyclerView = findViewById(R.id.items_list)
        val users: ArrayList<User> = arrayListOf()

        users.add(User("Alex", "alex@mail.com", "3","12345"))
        users.add(User("Bob", "bob@mail.com", "2","qwerty"))
        users.add(User("John", "john@mail.com", "1", "johny"))

        usersList.layoutManager = LinearLayoutManager(this)
        usersList.adapter = UsersAdapter(users)

        productsLink.setOnClickListener {
            val intent = Intent(this, ItemsActivity::class.java)
            startActivity(intent)
        }
    }
}