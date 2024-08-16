package com.example.http_excercise.list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.http_excercise.R
import com.example.http_excercise.data.network.response.ProductResponse
import com.example.http_excercise.databinding.FragmentListBinding
import kotlinx.coroutines.launch

class ProductListFragment : Fragment(R.layout.fragment_list) {

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding
        get() = _binding!!

    private val viewModel: ProductListViewModel by viewModels(factoryProducer = { ProductListViewModel.factory })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)
        observeUiState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {

                    when (it) {
                        ProductListUiState.Loading -> {
                            binding.progressCircular.isVisible = true
                            binding.ivConnectionError.isVisible = false
                        }

                        is ProductListUiState.Success -> setUpRecyclerView(product = it.products)
                        ProductListUiState.Error -> {
                            binding.progressCircular.isVisible = false
                            binding.ivConnectionError.isVisible = true
                        }
                    }
                }
            }
        }
    }

    private fun setUpRecyclerView(product: List<ProductResponse>) {
        binding.progressCircular.isVisible = false
        binding.ivConnectionError.isVisible = false
        val adapter = ProductAdapter(product) {
            val action = ProductListFragmentDirections.actionCoinListFragmentToCoinDetailFragment(it)
            findNavController().navigate(action)
        }

        binding.rvCoins.adapter = adapter
    }
}