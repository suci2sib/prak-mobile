package com.example.suciapps

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.suciapps.databinding.ActivityBaseBinding
// Import fragment dari package-nya masing-masing
import com.example.suciapps.home.HomeFragment
import com.example.suciapps.message.MessageFragment
import com.example.suciapps.more.MoreFragment

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur padding agar UI tidak terpotong,
        // tapi bottom diset 0 agar BottomNav menempel ke bawah layar.
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        /** 1. Set HomeFragment sebagai tampilan default saat pertama kali dibuka */
        replaceFragment(HomeFragment())

        /** 2. Logika pindah fragment saat item menu diklik */
        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.message -> {
                    replaceFragment(MessageFragment())
                    true
                }
                R.id.more -> {
                    replaceFragment(MoreFragment())
                    true
                }
                else -> false
            }
        }
    }

    /** * Fungsi untuk mengganti fragment di dalam fragment_container
     */
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            /** * .addToBackStack(null) dinonaktifkan agar saat user menekan tombol Back,
             * aplikasi langsung tertutup (tidak kembali ke fragment sebelumnya).
             */
            .commit()
    }
}