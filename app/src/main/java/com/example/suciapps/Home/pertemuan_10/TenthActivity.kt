package com.example.suciapps.home.pertemuan_10

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.suciapps.R
import com.example.suciapps.databinding.ActivityTenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class TenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur padding untuk Edge-to-Edge screen
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Setup Toolbar dan Aksi Tombol Back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // 1. Inisialisasi Adapter
        val tabsAdapter = TenthTabsAdapter(this)

        // 2. Set adapter ke ViewPager2
        binding.viewPager.adapter = tabsAdapter

        // 3. Hubungkan TabLayout & ViewPager2 dengan konfigurasi 3 Tab
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Tab A"
                    // Opsional: Buka komen di bawah jika ingin pakai Icon & Badge
                     tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_home)
                     val badge = tab.getOrCreateBadge()
                     badge.isVisible = true
                }
                1 -> {
                    tab.text = "Tab B"
                    // Opsional: Buka komen di bawah jika ingin pakai Icon & Badge
                     tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_home)
                     val badge = tab.getOrCreateBadge()
                     badge.isVisible = true
                     badge.number = 5
                }
                2 -> {
                    tab.text = "Produk" // Judul untuk Tab C yang berisi RecyclerView
                }
            }
        }.attach()
    }
}