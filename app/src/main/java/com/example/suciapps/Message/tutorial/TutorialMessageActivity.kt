package com.example.suciapps.Message.tutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.suciapps.databinding.ActivityTutorialMessageBinding

class TutorialMessageActivity : AppCompatActivity() {

    // Mengaktifkan View Binding untuk Activity
    private lateinit var binding: ActivityTutorialMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup ViewPager dengan adapter sesuai panduan modul
        val fragmentsList = listOf(
            Tutorial1Fragment(),
            Tutorial2Fragment(),
            Tutorial3Fragment()
        )

        val adapter = TutorialFragmentAdapter(this, fragmentsList)
        binding.tutorialMessageViewPager.adapter = adapter

        // 3. Hubungkan DotsIndicator dengan TutorialMessageViewPager
        binding.dotIndicator.attachTo(binding.tutorialMessageViewPager)
    }
}