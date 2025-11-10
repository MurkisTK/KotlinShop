package com.example.shop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

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

        val map = mapOf(
            "i1" to R.drawable.i1,
            "i2" to R.drawable.i2,
            "i3" to R.drawable.i3,
        )
        image.setImageResource(map[intent.getStringExtra("image")]!!)

        buyButton.setOnClickListener {
            val intent = Intent(this, ItemsActivity::class.java)
            startActivity(intent)
        }
    }
}