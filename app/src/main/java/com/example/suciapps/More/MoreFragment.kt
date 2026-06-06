package com.example.suciapps.more

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suciapps.data.model.AppDatabase
import com.example.suciapps.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {

    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: TodoAdapter
    private lateinit var database: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Inisialisasi Database
        database = AppDatabase.getDatabase(requireContext())

        // 2. Siapkan Adapter & RecyclerView
        adapter = TodoAdapter()
        binding.rvTodos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTodos.adapter = adapter

        // 3. Ambil data To-Do dari Room Database dan pantau perubahannya
        database.todoDao().getAllTodos().observe(viewLifecycleOwner) { todoList ->
            adapter.setTodos(todoList)
        }

        // 4. Aksi ketika tombol tambah tugas (FAB) diklik
        binding.fabAddTodo.setOnClickListener {
            val intent = Intent(requireContext(), TodoFormActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}