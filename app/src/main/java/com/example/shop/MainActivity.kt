package com.example.shop

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = DbHelper(this, null)

        val userLogin: EditText = findViewById(R.id.user_login)
        val userPhone: EditText = findViewById(R.id.user_phone_number)
        val userEmail: EditText = findViewById(R.id.user_email)
        val userPassword: EditText = findViewById(R.id.user_password)
        val button: Button = findViewById(R.id.registration_button)

        button.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val phone = userPhone.text.toString().trim()
            val email = userEmail.text.toString().trim()
            val password = userPassword.text.toString().trim()

            if(login == "" || password == "" || email == "" || phone == "")
                Toast.makeText(this, "Not all fields contains values", Toast.LENGTH_LONG).show()
            else {
                val user = User(login, email, phone, password)
                dbHelper.addUser(user)
                Toast.makeText(this, "User $login was created", Toast.LENGTH_LONG).show()

                userLogin.text.clear()
                userEmail.text.clear()
                userPassword.text.clear()
                userPhone.text.clear()
            }
        }
    }
}