package com.example.suciapps

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.suciapps.databinding.ActivityMainBinding
// Import semua Activity tujuan
import com.example.suciapps.pertemuan_2.SecondActivity
import com.example.suciapps.pertemuan_3.ThirdActivity
import com.example.suciapps.pertemuan_4.FourthActivity
import com.example.suciapps.pertemuan_5.FifthActivity
import com.example.suciapps.pertemuan_6.AuthActivity
import com.example.suciapps.pertemuan_7.SeventhActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Setup View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur padding agar UI tidak tertutup status bar/notch
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // --- NAVIGASI TOMBOL ---

        // 1. Pertemuan 2
        binding.btnToSecond.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        // 2. Pertemuan 3
        binding.btnToThird.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }

        // 3. Pertemuan 4
        binding.btnToFourth.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java).apply {
                putExtra("nama", "Politeknik Caltex Riau")
                putExtra("asal", "Rumbai")
                putExtra("umur", 25)
            }
            startActivity(intent)
        }

        // 4. Pertemuan 5
        binding.btnToFifth.setOnClickListener {
            val intent = Intent(this, FifthActivity::class.java)
            startActivity(intent)
        }

        // 5. Pertemuan 7
        binding.btnToSeventh.setOnClickListener {
            val intent = Intent(this, SeventhActivity::class.java)
            startActivity(intent)
        }

        // 6. Tombol Logout
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    // Hapus session login
                    sharedPref.edit { clear() }

                    // Pindah ke halaman Login (AuthActivity)
                    val intent = Intent(this, AuthActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}