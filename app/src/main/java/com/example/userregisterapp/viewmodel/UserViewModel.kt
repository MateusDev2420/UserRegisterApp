package com.example.userregisterapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.userregisterapp.database.AppDatabase
import com.example.userregisterapp.model.User

import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao = AppDatabase.getDatabase(application).userDao()


    val allUsers: LiveData<List<User>> = userDao.getAllUsers()
    fun insertUser(user: User) {
        viewModelScope.launch {
            try {
                userDao.insert(user)
                Log.i("UserViewModel", "Usuário ${user.name} inserido com sucesso!")
            } catch (e: Exception) {
                Log.e("UserViewModel", "Erro ao inserir usuário: ${e.message}")
            }
        }
    }
}