package com.example.suciapps.pertemuan_6

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.suciapps.BaseActivity
import com.example.suciapps.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {
            val user = binding.username.text.toString().trim()
            val password = binding.pass.text.toString().trim()

            if (user == password && user.isNotEmpty()) {
                // 1. Simpan status login ke SharedPreferences
                val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putBoolean("isLogin", true)
                    apply()
                }

                // 2. Pindah ke BaseActivity (Home)
                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
                finish() 
            } else {
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