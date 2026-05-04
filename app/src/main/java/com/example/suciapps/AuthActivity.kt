package com.example.suciapps

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Mengaktifkan fitur edge-to-edge agar konsisten dengan activity lain
        enableEdgeToEdge()
        setContentView(R.layout.activity_auth)

        // Mengatur padding agar UI tidak tertutup status bar/navigasi
        val mainView = findViewById<View>(R.id.main)
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.pass)
        val btnLogin = findViewById<Button>(R.id.button2)

        btnLogin.setOnClickListener {
            val user = username.text.toString().trim()
            val pass = password.text.toString().trim()

            // Sekarang login diperbolehkan selama username dan password tidak kosong
            if (user.isNotEmpty() && pass.isNotEmpty()) {
                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", user) // Menyimpan username yang diinput
                editor.apply()

                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Username dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}