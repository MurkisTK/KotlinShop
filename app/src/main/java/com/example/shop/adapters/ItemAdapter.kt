package com.example.shop.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.models.Item
import com.example.shop.R
import com.example.shop.activities.ItemActivity

class ItemAdapter(val items: List<Item>, val context: Context) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder, position: Int
    ) {
        holder.title.text = items[position].title
        holder.price.text = context.getString(R.string.price, items[position].price.toString())
        holder.desc.text = items[position].desc

        val map = mapOf(
            "i1" to R.drawable.i1,
            "i2" to R.drawable.i2,
            "i3" to R.drawable.i3,
        )
        holder.image.setImageResource(map[items[position].image]!!)

        holder.itemButton.setOnClickListener {
            val intent = Intent(context, ItemActivity::class.java)

            intent.putExtra("title", items[position].title)
            intent.putExtra("desc", items[position].text)
            intent.putExtra("image", items[position].image)
            intent.putExtra("price", items[position].price)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)
        val title: TextView = view.findViewById(R.id.title)
        val desc: TextView = view.findViewById(R.id.description)
        val price: TextView = view.findViewById(R.id.price)
        val itemButton: Button = view.findViewById(R.id.item_button)
    }
}