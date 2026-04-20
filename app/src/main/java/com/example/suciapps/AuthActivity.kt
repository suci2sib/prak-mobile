package com.example.suciapps.pertemuan_6

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.suciapps.MainActivity
import com.example.suciapps.databinding.ActivityAuthBinding // Import class binding

class AuthActivity : AppCompatActivity() {

    // Inisialisasi variabel binding
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Setup View Binding
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Logika Tombol Login menggunakan ID dari XML kamu
        binding.button2.setOnClickListener {
            val user = binding.username.text.toString().trim()
            val password = binding.pass.text.toString().trim()

            // Sesuai instruksi: Jika Username == Password
            if (user == password && user.isNotEmpty()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Agar user tidak bisa kembali ke login lewat tombol back
            } else {
                // Jika tidak sama, tampilkan AlertDialog
                showErrorDialog()
            }
        }
    }

    private fun showErrorDialog() {
        AlertDialog.Builder(this)
            .setTitle("Login Gagal")
            .setMessage("Silahkan coba lagi")
            .setPositiveButton("OK", null)
            .show()
    }
}