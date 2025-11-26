package com.example.shop.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.shop.ImageMapper
import com.example.shop.R
import kotlin.collections.get

class ItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        val title: TextView = findViewById(R.id.title)
        val desc: TextView = findViewById(R.id.description)
        val price: TextView = findViewById(R.id.price)
        val buyButton: Button = findViewById(R.id.buy_button)
        val image: ImageView = findViewById(R.id.image)

        title.text = intent.getStringExtra("title")
        price.text = getString(R.string.price, intent.getIntExtra("price", 0).toString())
        desc.text = intent.getStringExtra("desc")

        image.setImageResource(ImageMapper.images[intent.getStringExtra("image")]!!)

        buyButton.setOnClickListener {
            val intent = Intent(this, ItemsActivity::class.java)
            startActivity(intent)
        }
    }
}