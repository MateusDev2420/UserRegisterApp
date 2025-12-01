package com.example.userregisterapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.userregisterapp.model.User
import com.example.userregisterapp.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {


    private val userViewModel: UserViewModel by viewModels()

    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonRegister: Button
    private lateinit var buttonListUsers: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonRegister = findViewById(R.id.buttonRegister)


        buttonListUsers = findViewById(R.id.buttonListUsers)


        buttonRegister.setOnClickListener {
            registerUser()
        }


        buttonListUsers.setOnClickListener {
            val intent = Intent(this, UserListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registerUser() {
        val name = editTextName.text.toString().trim()
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return
        }

        val newUser = User(name = name, email = email, password = password)
        userViewModel.insertUser(newUser)

        Toast.makeText(this, "Usuário $name cadastrado com sucesso!", Toast.LENGTH_LONG).show()
        clearFields()
    }

    private fun clearFields() {
        editTextName.text.clear()
        editTextEmail.text.clear()
        editTextPassword.text.clear()
        editTextName.clearFocus()
    }
}