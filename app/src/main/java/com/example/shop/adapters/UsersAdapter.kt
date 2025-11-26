package com.example.shop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.models.User
import com.example.shop.R

class UsersAdapter(val items: List<User>) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: UserViewHolder, position: Int
    ) {
        holder.login.text = items[position].login
        holder.phone.text = items[position].phone
        holder.email.text = items[position].email
        holder.password.text = items[position].password
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val login: TextView = view.findViewById(R.id.login)
        val phone: TextView = view.findViewById(R.id.phone)
        val email: TextView = view.findViewById(R.id.email)
        val password: TextView = view.findViewById(R.id.password)
    }
}