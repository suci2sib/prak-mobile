package com.example.suciapps.more

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.suciapps.data.entity.TodoEntity
import com.example.suciapps.databinding.ItemTodoBinding

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private val todoList = ArrayList<TodoEntity>()

    fun setTodos(todos: List<TodoEntity>) {
        todoList.clear()
        todoList.addAll(todos)
        notifyDataSetChanged()
    }

    inner class TodoViewHolder(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: TodoEntity) {
            binding.tvTaskName.text = todo.taskName
            binding.tvDueDate.text = "Deadline: ${todo.dueDate}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

    override fun getItemCount(): Int = todoList.size
}