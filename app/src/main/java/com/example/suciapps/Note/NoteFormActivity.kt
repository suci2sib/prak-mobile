package com.example.suciapps.Note

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.suciapps.data.entity.NoteEntity
import com.example.suciapps.data.model.AppDatabase
import com.example.suciapps.databinding.ActivityNoteFormBinding
import kotlinx.coroutines.launch

class NoteFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteFormBinding
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Panggil database
        database = AppDatabase.getDatabase(this)

        // Aksi ketika tombol "Simpan" diklik
        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val desc = binding.etDescription.text.toString()

            // Cek apakah form tidak kosong
            if (title.isNotEmpty() && desc.isNotEmpty()) {
                val note = NoteEntity(title = title, description = desc)

                // Simpan ke database di latar belakang (coroutine)
                lifecycleScope.launch {
                    database.noteDao().insert(note)
                    runOnUiThread {
                        Toast.makeText(this@NoteFormActivity, "Catatan Disimpan!", Toast.LENGTH_SHORT).show()
                        finish() // Tutup halaman ini dan kembali ke daftar catatan
                    }
                }
            } else {
                Toast.makeText(this, "Judul dan isi tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}