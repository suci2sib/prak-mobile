package com.example.suciapps.more

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.suciapps.data.entity.TodoEntity
import com.example.suciapps.data.model.AppDatabase
import com.example.suciapps.databinding.ActivityTodoFormBinding
import kotlinx.coroutines.launch

class TodoFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTodoFormBinding
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = AppDatabase.getDatabase(this)

        binding.btnSaveTodo.setOnClickListener {
            val task = binding.etTaskName.text.toString()
            val date = binding.etDueDate.text.toString()

            if (task.isNotEmpty() && date.isNotEmpty()) {
                val todo = TodoEntity(taskName = task, dueDate = date)

                lifecycleScope.launch {
                    database.todoDao().insert(todo)
                    runOnUiThread {
                        Toast.makeText(this@TodoFormActivity, "Tugas Ditambahkan!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            } else {
                Toast.makeText(this, "Semua kolom harus diisi!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}