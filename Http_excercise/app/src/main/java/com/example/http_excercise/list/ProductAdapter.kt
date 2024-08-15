package com.example.http_excercise.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.http_excercise.data.network.response.ProductResponse
import com.example.http_excercise.databinding.ItemProductBinding


class ProductAdapter(
    private val coins: List<ProductResponse>,
    private val onCoinClick: (String) -> Unit,
) : RecyclerView.Adapter<ProductAdapter.CoinViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return CoinViewHolder(binding, onCoinClick)
    }

    override fun getItemCount(): Int {
        return coins.size
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(coins[position])
    }

    inner class CoinViewHolder(
        private val binding: ItemProductBinding,
        onCoinClick: (String) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(coin: ProductResponse) {
            binding.productName.text = coin.title
            binding.productPrice.text = coin.price.toString()
            binding.productCategory.text = coin.category
            Glide.with(binding.productImage.context).load(coin.thumbnail).into(binding.productImage)

            binding.root.setOnClickListener {
                onCoinClick(coin.id)
            }
        }


    }
}