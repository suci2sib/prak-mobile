package com.example.suciapps.note

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suciapps.Note.NoteAdapter
import com.example.suciapps.Note.NoteFormActivity
import com.example.suciapps.data.model.AppDatabase
import com.example.suciapps.databinding.FragmentNoteBinding

class FragmentNote : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: NoteAdapter
    private lateinit var database: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Catatan: Pastikan nama file XML untuk fragment ini adalah fragment_note.xml
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Inisialisasi Database
        database = AppDatabase.getDatabase(requireContext())

        // 2. Siapkan Adapter & RecyclerView
        adapter = NoteAdapter()
        binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNotes.adapter = adapter

        // 3. Ambil data dari Room Database dan pantau perubahannya
        database.noteDao().getAllNotes().observe(viewLifecycleOwner) { noteList ->
            adapter.setNotes(noteList)
        }

        // 4. Aksi ketika tombol tambah (FAB) diklik
        binding.fabAddNote.setOnClickListener {
            // Kita akan arahkan ke halaman NoteFormActivity untuk mengetik catatan
            val intent = Intent(requireContext(), NoteFormActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}