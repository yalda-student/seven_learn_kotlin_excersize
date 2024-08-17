package com.example.http_excercise.list

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.http_excercise.R
import kotlin.Int
import kotlin.String

public class ProductListFragmentDirections private constructor() {
  private data class ActionCoinListFragmentToCoinDetailFragment(
    public val productId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_coinListFragment_to_coinDetailFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("productId", this.productId)
        return result
      }
  }

  public companion object {
    public fun actionCoinListFragmentToCoinDetailFragment(productId: String): NavDirections =
        ActionCoinListFragmentToCoinDetailFragment(productId)
  }
}
