package com.example.suciapps.Home.pertemuan_9

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.suciapps.databinding.ActivityNinthBinding
import com.google.android.material.chip.Chip
import com.google.android.material.button.MaterialButton // Tambahkan ini

class NinthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNinthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNinthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Setup Toolbar
        setSupportActionBar(binding.toolbar)
        // supportActionBar?.setDisplayHomeAsUpEnabled(true) // Hapus ini jika di XML sudah ada navigationIcon

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // 2. Handle Tombol Login dengan Validasi Email yang lebih baik
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            if (email.isEmpty()) {
                binding.textInputLayout.error = "Email tidak boleh kosong"
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.textInputLayout.error = "Format email salah"
            } else {
                binding.textInputLayout.error = null
                Toast.makeText(this, "Login berhasil untuk: $email", Toast.LENGTH_SHORT).show()
            }
        }

        // 3. Chip Group Listener (Ambil ID pertama yang dipilih)
        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                Toast.makeText(this, "Filter aktif: ${chip.text}", Toast.LENGTH_SHORT).show()
            }
        }

        // 4. Handle Klik pada Menu di GridLayout (Tambahan)
        // Karena di XML kamu pakai MaterialButton di dalam GridLayout
        setupGridMenu()
    }

    private fun setupGridMenu() {
        // Meloop semua anak (tombol) di dalam GridLayout agar bisa diklik
        for (i in 0 until binding.gridLayoutMenu.childCount) {
            val child = binding.gridLayoutMenu.getChildAt(i)
            if (child is MaterialButton) {
                child.setOnClickListener {
                    Toast.makeText(this, "Klik: ${child.text}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}