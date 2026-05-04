package com.example.suciapps.message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.suciapps.R
import com.example.suciapps.databinding.ItemMessageBinding
import com.google.android.material.snackbar.Snackbar

class MessageAdapter(
    context: Context,
    private val Messages: List<MessageModel>
) : ArrayAdapter<MessageModel>(context, 0, Messages) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemMessageBinding = ItemMessageBinding.inflate(
            LayoutInflater.from(context), 
            parent, 
            false
        )
        val view = binding.root

        val data = Messages[position]

        // Menampilkan foto profil menggunakan Glide
        Glide.with(context)
            .load(data.avatarUrl)
            .placeholder(R.drawable.avatar_img) // Muncul saat loading
            .error(R.drawable.avatar_img)       // Muncul jika URL error
            .into(binding.avatarImg)

        // Menampilkan nama pengirim dan isi pesan
        binding.textSender.text = data.senderName
        binding.textMessage.text = data.messageText

        // 8️⃣ Terapkan OnClick pada setiap item
        view.setOnClickListener {
            Snackbar.make(
                parent,
                "Pesan dari ${data.senderName}: ${data.messageText}",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        return view
    }
}
