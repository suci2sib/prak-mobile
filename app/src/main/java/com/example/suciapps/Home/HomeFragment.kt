package com.example.suciapps.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.suciapps.AuthActivity
import com.example.suciapps.databinding.FragmentHomeBinding
import com.example.suciapps.Home.pertemuan_2.SecondActivity
import com.example.suciapps.Home.pertemuan_3.ThirdActivity
import com.example.suciapps.Home.pertemuan_4.FourthActivity
import com.example.suciapps.Home.pertemuan_5.FifthActivity
import com.example.suciapps.Home.pertemuan_7.SeventhActivity
import com.example.suciapps.Home.pertemuan_9.NinthActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar agar muncul judul "Home"
        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Home"

        setupNavigation()
    }

    private fun setupNavigation() {
        binding.apply {
            // Navigasi Standar
            btnToSecond.setOnClickListener { move(SecondActivity::class.java) }
            btnToThird.setOnClickListener { move(ThirdActivity::class.java) }

            // Navigasi dengan Data (Pertemuan 4)
            btnToFourth.setOnClickListener {
                val intent = Intent(requireContext(), FourthActivity::class.java).apply {
                    putExtra("nama", "Politeknik Caltex Riau")
                    putExtra("asal", "Rumbai")
                    putExtra("umur", 25)
                }
                startActivity(intent)
            }

            btnToFifth.setOnClickListener { move(FifthActivity::class.java) }
            btnToSeventh.setOnClickListener { move(SeventhActivity::class.java) }

            // Tombol Pertemuan 9 (Ditambah pengaman agar tidak langsung crash)
            btnToNinth.setOnClickListener {
                try {
                    val intent = Intent(requireContext(), NinthActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    android.util.Log.e("ERROR_SUCI", "Gagal buka Halaman 9: ${e.message}")
                    Toast.makeText(requireContext(), "Gagal buka Halaman 9, cek Logcat!", Toast.LENGTH_SHORT).show()
                }
            }

            // Fungsi Logout
            btnLogout.setOnClickListener {
                val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                sharedPref.edit { clear() }
                move(AuthActivity::class.java)
                requireActivity().finish()
            }
        }
    }

    // Fungsi pembantu (helper) biar kode lebih pendek
    private fun move(cls: Class<*>) {
        startActivity(Intent(requireContext(), cls))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}