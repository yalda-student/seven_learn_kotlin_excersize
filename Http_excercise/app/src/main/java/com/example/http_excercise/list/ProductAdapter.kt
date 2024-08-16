package com.example.http_excercise.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.http_excercise.data.network.response.ProductResponse
import com.example.http_excercise.databinding.ItemProductBinding


class ProductAdapter(
    private val product: List<ProductResponse>,
    private val onProductClick: (String) -> Unit,
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return product.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(product[position])
    }

    inner class ProductViewHolder(
        private val binding: ItemProductBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductResponse) {
            binding.productName.text = product.title
            binding.productPrice.text = product.price.toString()
            binding.productCategory.text = product.category
            Glide.with(binding.productImage.context).load(product.thumbnail).into(binding.productImage)

            binding.root.setOnClickListener {
                onProductClick(product.id)
            }
        }


    }
}