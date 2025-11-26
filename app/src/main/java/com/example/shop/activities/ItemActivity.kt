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
    private lateinit var title: TextView
    private lateinit var desc: TextView
    private lateinit var price: TextView
    private lateinit var buyButton: Button
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        initialize()

        val priceValue = intent.getIntExtra("price", 0)

        title.text = intent.getStringExtra("title")
        price.text = getString(R.string.price, priceValue.toString())
        desc.text = intent.getStringExtra("desc")
        image.setImageResource(ImageMapper.images[intent.getStringExtra("image")]!!)

        buyButton.setOnClickListener {
            val intent = Intent(this, ItemsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initialize() {
        title = findViewById(R.id.title)
        desc = findViewById(R.id.description)
        price = findViewById(R.id.price)
        buyButton = findViewById(R.id.buy_button)
        image = findViewById(R.id.image)
    }
}