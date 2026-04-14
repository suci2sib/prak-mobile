package com.example.suciapps.pertemuan_5

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.suciapps.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    // Menggunakan ViewBinding sesuai dengan modul
    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi binding
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengaktifkan toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Web Merdeka"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // Konfigurasi awal WebView
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("https://merdeka.com")

        // --- 🌟 IMPROVISASI: Menambahkan Loading WebChromeClient 🌟 ---
        // Ini akan mengubah judul Toolbar menjadi persentase loading saat web dimuat
        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress < 100) {
                    // Tampilkan judul loading jika web belum 100% termuat
                    supportActionBar?.title = "Loading... $newProgress%"
                } else {
                    // Kembalikan ke judul asli jika web sudah selesai termuat
                    supportActionBar?.title = "Web Merdeka"
                }
            }
        }

        // Agar Toolbar hide/show (menghilang/muncul) saat scroll web
        binding.webView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY) {
                binding.appBar.setExpanded(false, true) // Sembunyikan saat scroll ke bawah
            } else if (scrollY < oldScrollY) {
                binding.appBar.setExpanded(true, true) // Tampilkan saat scroll ke atas
            }
        }
    }

    // Mengaktifkan tombol back bawaan HP agar kembali ke halaman web sebelumnya, bukan langsung keluar
    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack() // Kembali ke halaman web sebelumnya
        } else {
            super.onBackPressed() // Keluar dari activity jika tidak ada history web lagi
        }
    }

    // Mengaktifkan fungsi tombol panah back di sudut kiri atas Toolbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() // Memanggil fungsi onBackPressed di atas
        return true
    }
}