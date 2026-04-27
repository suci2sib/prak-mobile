package com.example.suciapps.Home.pertemuan_7

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.suciapps.R
import com.example.suciapps.databinding.ActivitySeventhBinding

class SeventhActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeventhBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Setup View Binding
        binding = ActivitySeventhBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Penanganan padding status bar agar Toolbar tidak tertutup
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // --- LOGIKA FRAGMENT ---

        // Menampilkan SatuFragment sebagai tampilan awal (default)
        replaceFragment(SatuFragment())

        // Klik tombol Fragment 1
        binding.btnFragment1.setOnClickListener {
            replaceFragment(SatuFragment())
        }

        // Klik tombol Fragment 2
        binding.btnFragment2.setOnClickListener {
            replaceFragment(DuaFragment())
        }

        // Klik tombol Fragment 3
        binding.btnFragment3.setOnClickListener {
            replaceFragment(TigaFragment())
        }
    }

    /**
     * Fungsi untuk mengganti fragment di dalam FrameLayout (fragment_container)
     */
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        // R.id.fragment_container adalah ID FrameLayout di XML kamu
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

    // Navigasi tombol back di toolbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}