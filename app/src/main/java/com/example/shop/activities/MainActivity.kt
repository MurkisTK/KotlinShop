package com.example.shop.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shop.R
import com.example.shop.database.ProductsDbHelper
import com.example.shop.models.User
import com.example.shop.database.UserDbHelper
import com.example.shop.models.Item

class MainActivity : AppCompatActivity() {
    private lateinit var userLogin: EditText
    private lateinit var userPhone: EditText
    private lateinit var userEmail: EditText
    private lateinit var userPassword: EditText
    private lateinit var button: Button
    private lateinit var authLink: TextView

    private lateinit var dbHelper: UserDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()

        authLink.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }
        button.setOnClickListener {
            handleRegistration()
        }
    }

    fun handleRegistration() {
        val login = userLogin.text.toString().trim()
        val phone = userPhone.text.toString().trim()
        val email = userEmail.text.toString().trim()
        val password = userPassword.text.toString().trim()

        if (!validateFields(login, password, email, phone)) Toast.makeText(
            this, "Fields aren't valid", Toast.LENGTH_LONG
        ).show()
        else {
            val user = User(login, email, phone, password)
            dbHelper.addUser(user)
            Toast.makeText(this, "User $login was created", Toast.LENGTH_LONG).show()

            clearFields()
        }
    }

    fun validateFields(login: String, password: String, email: String, phone: String): Boolean {
        if (login == "" || password == "" || email == "" || phone == "") {
            return false
        }

        if (password.length < 6) return false

        if (!email.contains("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$".toRegex())) return false

        if (!phone.contains("^\\+?[0-9\\s\\-\\(\\)]{10,}$".toRegex())) return false

        return true
    }

    fun clearFields() {
        userLogin.text.clear()
        userEmail.text.clear()
        userPassword.text.clear()
        userPhone.text.clear()
    }

    fun initialize() {
        initializeDb()

        userLogin = findViewById(R.id.user_login)
        userPhone = findViewById(R.id.user_phone_number)
        userEmail = findViewById(R.id.user_email)
        userPassword = findViewById(R.id.user_password)
        button = findViewById(R.id.registration_button)
        authLink = findViewById<TextView>(R.id.authorization_link)
    }

    fun initializeDb() {
        dbHelper = UserDbHelper(this, null)
        val productsDbHelper = ProductsDbHelper(this, null)

        dbHelper.addUser(User("a", "a", "a", "a"))

        productsDbHelper.addItem(Item(1, "i1", "t1", "d1", "text1", 100))
        productsDbHelper.addItem(Item(2, "i2", "t2", "d2", "text2", 200))
        productsDbHelper.addItem(Item(3, "i3", "t3", "d3", "text3", 300))
    }
}