package com.example.userregisterapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userregisterapp.adapter.UserAdapter
import com.example.userregisterapp.viewmodel.UserViewModel

class UserListActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewUsers)
        adapter = UserAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observar dados do ViewModel [cite: 394]
        userViewModel.allUsers.observe(this) { users ->
            adapter.setData(users)
        }
    }
}