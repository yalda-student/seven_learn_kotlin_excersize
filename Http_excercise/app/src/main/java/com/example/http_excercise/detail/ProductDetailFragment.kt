package com.example.http_excercise.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.http_excercise.R
import com.example.http_excercise.databinding.FragmentDetailBinding
import kotlinx.coroutines.launch

class ProductDetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = _binding!!

    private val viewModel: ProductDetailViewModel by viewModels(factoryProducer = { ProductDetailViewModel.factory  })
    private val args: ProductDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)
        observeUiState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchProductData(args.productId)
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    binding.progressCircular.isVisible = it is ProductDetailUiState.Loading

                    if (it is ProductDetailUiState.ProductData) {
                        showProductDetail(
                            it
                        )
                    }
                }
            }

        }
    }

    private fun showProductDetail(
        product: ProductDetailUiState.ProductData
    ) {

        binding.productName.text = product.name
        binding.description.text = product.description
        binding.productCategory.text = product.category
        binding.productBrand.text = product.brand
        binding.productPrice.text = product.price.toString()
        binding.productRating.text = product.rating.toString()
        Glide.with(this).load(product.thumbnail).into(binding.productThumbnail)

    }
}