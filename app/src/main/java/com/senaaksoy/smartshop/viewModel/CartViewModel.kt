package com.senaaksoy.smartshop.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
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

    val totalPrice: LiveData<Double> = cartRepository.cartItems.map { cartItems ->
        cartItems.sumOf { it.price * it.quantity }
    }
    fun addToCart(cartEntity: CartEntity) {
        viewModelScope.launch {

            val existingCartItem = cartItems.value?.find { it.id == cartEntity.id }

            if (existingCartItem != null) {


                cartRepository.updateQuantity(existingCartItem.id, existingCartItem.quantity)
            } else {

                cartRepository.addToCart(cartEntity.copy(quantity = 1))
            }
        }
    }






    fun updateQuantity(id: Int, newQuantity: Int) {
        viewModelScope.launch {
            if (newQuantity <= 0) {
               cartRepository.removeFromCart(id)
            } else {
                cartRepository.updateQuantity(id, newQuantity)
            }
        }
    }


}
