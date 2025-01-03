package com.senaaksoy.smartshop.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senaaksoy.smartshop.repository.CartRepository
import com.senaaksoy.smartshop.roomDb.CartEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {
    val cartItems = cartRepository.cartItems

    fun addToCart(cartEntity: CartEntity) {
        viewModelScope.launch {
            cartRepository.addToCart(cartEntity)
        }
    }

    fun updateQuantity(id: Int, quantity: Int) {
        viewModelScope.launch {
            cartRepository.updateQuantity(id, quantity)
        }
    }
}
