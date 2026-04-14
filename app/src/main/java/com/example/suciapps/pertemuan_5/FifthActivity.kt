package com.example.suciapps.pertemuan_5

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.suciapps.R

class FifthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fifth)

        // Mengatur padding agar tidak tertutup status bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- MATERI 1: TOOLBAR ---
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            title = "Activity Fifth"
            subtitle = "Ini adalah subtitle"

            // Menampilkan tombol back bawaan
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)

            // --- MATERI 5: VECTOR ASSET (Mengganti Ikon Back) ---
            // Pastikan ic_arrow_back sudah kamu buat di drawable!
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        // --- MATERI 4: WEBVIEW (Pindah Halaman) ---
        val btnWebView = findViewById<Button>(R.id.btnWebView)
        btnWebView.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }
    }

    // --- MATERI 2: OPTION MENU ---
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // Handle Tombol Back di Toolbar
            android.R.id.home -> {
                finish() // Menggunakan finish() lebih aman untuk menutup activity ini
                true
            }

            R.id.action_search -> {
                Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.sub_account -> {
                Toast.makeText(this, "Menu Account Dipilih", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.sub_privacy -> {
                Toast.makeText(this, "Menu Privacy Dipilih", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}