package com.example.shop.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.ImageMapper
import com.example.shop.R
import com.example.shop.models.ProductForBuy

class SelectedProductsAdapter(
    val items: List<ProductForBuy>,
    val context: Context,
    val update: (productId: Int, count: Int) -> Unit
) :
    RecyclerView.Adapter<SelectedProductsAdapter.ProductViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.selected_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder, position: Int
    ) {
        holder.count.text = context.getString(R.string.count, items[position].selected.count.toString())
        holder.price.text = context.getString(R.string.price, items[position].item.price.toString())
        holder.image.setImageResource(ImageMapper.images[items[position].item.image]!!)
        holder.plusButton.setOnClickListener {
            update(items[position].item.id, items[position].selected.count + 1)
        }
        holder.minusButton.setOnClickListener {
            update(items[position].item.id, items[position].selected.count - 1)
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)
        val count: TextView = view.findViewById(R.id.count)
        val price: TextView = view.findViewById(R.id.price)
        val plusButton: Button = view.findViewById(R.id.plus_button)
        val minusButton: Button = view.findViewById(R.id.minus_button)
    }
}