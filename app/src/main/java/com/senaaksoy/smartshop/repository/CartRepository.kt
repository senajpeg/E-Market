package com.senaaksoy.smartshop.repository

import androidx.lifecycle.LiveData
import com.senaaksoy.smartshop.roomDb.CartDao
import com.senaaksoy.smartshop.roomDb.CartEntity
import javax.inject.Inject


class CartRepository @Inject constructor(
    private val cartDao: CartDao
) {
    val cartItems: LiveData<List<CartEntity>> = cartDao.getCartItems()

    suspend fun addToCart(cartEntity: CartEntity) {
        cartDao.insert(cartEntity)
    }

    suspend fun updateQuantity(id: Int, quantity: Int) {
        cartDao.updateQuantity(id, quantity)
    }
    suspend fun removeFromCart(id: Int) {
        cartDao.deleteById(id)
    }
}
