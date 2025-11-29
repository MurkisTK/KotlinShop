package com.example.shop.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R
import com.example.shop.adapters.SelectedProductsAdapter
import com.example.shop.database.CommonDbHelper
import com.example.shop.models.ProductForBuy

class CartActivity : AppCompatActivity() {
    private lateinit var buyButton: Button
    private  lateinit var fullPrice: TextView
    private lateinit var itemsList: RecyclerView
    private lateinit var items: MutableList<ProductForBuy>
    private var dbHelper: CommonDbHelper = CommonDbHelper.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        initialize()

        buyButton.setOnClickListener {
            dbHelper.selectedProductsDbHelper.deleteProducts()
           finish()
        }
    }

    fun initialize() {
        buyButton = findViewById(R.id.buy_button)
        itemsList = findViewById<RecyclerView>(R.id.items_list)
        fullPrice = findViewById<TextView>(R.id.full_price)
        items = mutableListOf<ProductForBuy>()

        getProducts()

        itemsList.layoutManager = LinearLayoutManager(this)
        itemsList.adapter = SelectedProductsAdapter(items, this, this::updateCount)
    }

    fun getProducts(){
        val selectedProducts = dbHelper.selectedProductsDbHelper.getAllSelectedProducts()
        val items = selectedProducts.map {
            selected ->
            ProductForBuy(selected, dbHelper.productsDbHelper.getItemById(selected.product_id)!!)
        }.toList()

        val sum = items.sumOf { v -> v.selected.count * v.item.price }

        fullPrice.text = this.getString(R.string.price, sum.toString())

        this.items.clear()
        this.items.addAll(items)
        this.itemsList.adapter?.notifyDataSetChanged()
    }

    fun updateCount(productId: Int, count: Int){
        dbHelper.selectedProductsDbHelper.updateSelectedProducts(productId, count)

        getProducts()
    }
}