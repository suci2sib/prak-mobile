package com.example.suciapps.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.suciapps.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {

    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    private val dataListWithDesc = listOf(
        mapOf("title" to "Kotlin", "desc" to "Bahasa untuk Android modern"),
        mapOf("title" to "Java", "desc" to "Bahasa OOP yang populer"),
        mapOf("title" to "Python", "desc" to "Bahasa yang mudah dipahami"),
        mapOf("title" to "C++", "desc" to "Bahasa pemrograman tingkat tinggi"),
        mapOf("title" to "JavaScript", "desc" to "Bahasa untuk pengembangan web"),
        mapOf("title" to "Dart", "desc" to "Bahasa untuk framework Flutter"),
        mapOf("title" to "Swift", "desc" to "Bahasa untuk ekosistem Apple"),
        mapOf("title" to "Go", "desc" to "Bahasa dari Google untuk skalabilitas"),
        mapOf("title" to "PHP", "desc" to "Bahasa script untuk server-side"),
        mapOf("title" to "C#", "desc" to "Bahasa dari Microsoft untuk .NET"),
        mapOf("title" to "Rust", "desc" to "Bahasa dengan fokus keamanan memori")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Setup Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "More"

        // 2. Set SimpleAdapter dengan layout simple_list_item_2
        val adapter = SimpleAdapter(
            requireContext(),
            dataListWithDesc,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "desc"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        // 3. Hubungkan listViewItems dengan adapter
        binding.listViewItems.adapter = adapter

        // 4. Tambahkan aksi saat item di-list diklik
        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = dataListWithDesc[position]
            val title = selectedItem["title"]
            val desc = selectedItem["desc"]
            Toast.makeText(requireContext(), "Kamu memilih: $title ($desc)", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}