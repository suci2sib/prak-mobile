package com.example.suciapps.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
// Import Activity - Pastikan path ini sesuai dengan folder kamu
import com.example.suciapps.Home.pertemuan_2.SecondActivity
import com.example.suciapps.Home.pertemuan_3.ThirdActivity
import com.example.suciapps.Home.pertemuan_4.FourthActivity
import com.example.suciapps.Home.pertemuan_5.FifthActivity
import com.example.suciapps.Home.pertemuan_7.SeventhActivity
import com.example.suciapps.pertemuan_6.AuthActivity
import com.example.suciapps.databinding.FragmentHomeBinding
import androidx.core.content.edit

class HomeFragment : Fragment() {

    // 1. Inisialisasi Binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate layout menggunakan binding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 2. Setup Toolbar (Agar judul muncul di atas)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Home"
        }

        // 3. Tombol Navigasi ke Pertemuan-pertemuan
        binding.btnToSecond.setOnClickListener {
            val intent = Intent(requireContext(), SecondActivity::class.java)
            startActivity(intent)
        }

        binding.btnToThird.setOnClickListener {
            val intent = Intent(requireContext(), ThirdActivity::class.java)
            startActivity(intent)
        }

        binding.btnToFourth.setOnClickListener {
            val intent = Intent(requireContext(), FourthActivity::class.java).apply {
                putExtra("nama", "Politeknik Caltex Riau")
                putExtra("asal", "Rumbai")
                putExtra("umur", 25)
            }
            startActivity(intent)
        }

        binding.btnToFifth.setOnClickListener {
            val intent = Intent(requireContext(), FifthActivity::class.java)
            startActivity(intent)
        }

        binding.btnToSeventh.setOnClickListener {
            val intent = Intent(requireContext(), SeventhActivity::class.java)
            startActivity(intent)
        }

        // 4. Tombol Logout (Sesuai instruksi modul)
        binding.btnLogout.setOnClickListener {
            // Hapus session login
            val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            sharedPref.edit {
                clear()
            }

            // Kembali ke halaman Login (AuthActivity)
            val intent = Intent(requireContext(), AuthActivity::class.java)
            startActivity(intent)

            // Menutup BaseActivity agar tidak bisa di-Back
            requireActivity().finish()
        }
    }

    // 5. Membersihkan binding saat fragment hancur (mencegah memory leak)
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}