package com.example.suciapps.Home.pertemuan_6

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.suciapps.BaseActivity
import com.example.suciapps.R

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.pass)
        val btnLogin = findViewById<Button>(R.id.button2)

        btnLogin.setOnClickListener {
            val user = username.text.toString()
            val pass = password.text.toString()

            // Simple hardcoded login for practice
            if (user == "admin" && pass == "admin") {
                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                sharedPref.edit {
                    putBoolean("isLogin", true)
                }

                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Username atau Password Salah", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
