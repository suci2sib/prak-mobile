package com.example.suciapps

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
// Import ini penting agar MainActivity mengenali FourthActivity
import com.example.suciapps.pertemuan_4.FourthActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // 1. Inisialisasi tombol berdasarkan ID yang ada di activity_main.xml
        val btnToFourth = findViewById<Button>(R.id.btnToFourth)

        // 2. Memberikan aksi klik pada tombol
        btnToFourth.setOnClickListener {
            // Berpindah dari MainActivity ke FourthActivity
            val intent = Intent(this, FourthActivity::class.java)

            intent.putExtra("nama", "Politeknik Caltex Riau")
            intent.putExtra("asal", "Rumbai")
            intent.putExtra("umur", 25)

            startActivity(intent)
            finish()
        }
    }
}