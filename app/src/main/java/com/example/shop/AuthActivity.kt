package com.example.shop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val dbHelper = DbHelper(this, null)

        val userLogin: EditText = findViewById(R.id.user_login)
        val userPassword: EditText = findViewById(R.id.user_password)
        val authorizationButton: Button = findViewById(R.id.authorization_button)
        val deleteButton: Button = findViewById(R.id.delete_button)
        val regLink = findViewById<TextView>(R.id.registration_link)

        regLink.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        authorizationButton.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val password = userPassword.text.toString().trim()

            if (login == "" || password == "")
                Toast.makeText(this, "Not all fields contains values", Toast.LENGTH_LONG).show()
            else {
                val userExist = dbHelper.isUserExist(login, password)
                if (userExist)
                    Toast.makeText(this, "Successfully", Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(this, "Authorization error", Toast.LENGTH_LONG).show()
            }

            userPassword.text.clear()
            userLogin.text.clear()
        }
        deleteButton.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val password = userPassword.text.toString().trim()

            if (login == "" || password == "")
                Toast.makeText(this, "Not all fields contains values", Toast.LENGTH_LONG).show()
            else {
                val userExist = dbHelper.isUserExist(login, password)
                if (userExist){
                    dbHelper.deleteUser(login)
                    Toast.makeText(this, "Successfully", Toast.LENGTH_LONG).show()
                }
                else
                    Toast.makeText(this, "User cant be deleted", Toast.LENGTH_LONG).show()
            }

            userPassword.text.clear()
            userLogin.text.clear()
        }
    }
}