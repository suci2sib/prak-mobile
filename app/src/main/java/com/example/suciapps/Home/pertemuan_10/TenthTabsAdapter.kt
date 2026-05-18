package com.example.suciapps.home.pertemuan_10

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.suciapps.Home.pertemuan_10.TabAFragment
import com.example.suciapps.Home.pertemuan_10.TabBFragment

class TenthTabsAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    // 1. Jumlah total tab diubah dari 2 menjadi 3
    override fun getItemCount(): Int = 3

    // 2. Menentukan Fragment mana yang akan ditampilkan berdasarkan posisi tab
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabAFragment()
            1 -> TabBFragment()
            2 -> TabCFragment() // Tambahan untuk Tab C (Produk)
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}