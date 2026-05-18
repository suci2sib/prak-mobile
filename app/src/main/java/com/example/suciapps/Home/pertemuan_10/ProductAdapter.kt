package com.example.suciapps.home.pertemuan_10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suciapps.databinding.ItemProductBinding

class ProductAdapter(
    private val productList: List<ProductModel>,
    private val onItemClick: (ProductModel) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ItemProductBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = productList[position]
        with(holder.binding) {
            tvProductName.text = item.name
            tvProductPrice.text = item.price

            // Menggunakan Glide untuk load gambar dari URL Picsum
            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .into(imgProduct)

            // Aksi ketika item produk di klik
            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun getItemCount(): Int = productList.size
}