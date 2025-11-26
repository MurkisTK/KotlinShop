package com.example.shop.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shop.database.UserDbHelper
import com.example.shop.R

class AuthActivity : AppCompatActivity() {
    private lateinit var userLogin: EditText
    private lateinit var userPassword: EditText
    private lateinit var authorizationButton: Button
    private lateinit var deleteButton: Button
    private lateinit var regLink: TextView
    private val dbHelper = UserDbHelper(this, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        initialize()

        regLink.setOnClickListener { handleSwapToRegistration() }
        authorizationButton.setOnClickListener {
            defaultFieldsHandler({ _, _ ->
                val intent = Intent(this, ItemsActivity::class.java)
                startActivity(intent)
            }, "Successfully", "Authorization error")
        }
        deleteButton.setOnClickListener {
            defaultFieldsHandler({ login, _ ->
                dbHelper.deleteUser(login)
            }, "Successfully deleted", "Deletion error")
        }
    }

    private fun handleSwapToRegistration() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun defaultFieldsHandler(
        funcOnUserExist: (String, String) -> Unit,
        successMessage: String,
        failMessage: String
    ) {
        val login = userLogin.text.toString().trim()
        val password = userPassword.text.toString().trim()

        if (checkFieldsNotEmpty(login, password)) {
            val userExist = dbHelper.isUserExist(login, password)
            if (userExist) {
                funcOnUserExist(login, password)
                Toast.makeText(this, successMessage, Toast.LENGTH_LONG).show()
            } else
                Toast.makeText(this, failMessage, Toast.LENGTH_LONG).show()
        } else
            createEmptyFieldsToast()

        clearFields()
    }

    private fun checkFieldsNotEmpty(login: String, password: String): Boolean {
        return login != "" && password != ""
    }

    private fun createEmptyFieldsToast() {
        Toast.makeText(this, "Not all fields contains values", Toast.LENGTH_LONG).show()
    }

    private fun clearFields() {
        userPassword.text.clear()
        userLogin.text.clear()
    }

    private fun initialize() {
        this.userLogin = findViewById(R.id.user_login)
        this.userPassword = findViewById(R.id.user_password)
        this.authorizationButton = findViewById(R.id.authorization_button)
        this.deleteButton = findViewById(R.id.delete_button)
        this.regLink = findViewById<TextView>(R.id.registration_link)
    }
}