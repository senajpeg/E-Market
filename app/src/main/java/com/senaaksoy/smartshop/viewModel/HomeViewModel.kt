package com.senaaksoy.smartshop.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senaaksoy.smartshop.repository.ProductRepository
import com.senaaksoy.smartshop.roomDb.ProductEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    val products = productRepository.allProducts
    init {
        refreshProducts()
    }
    fun refreshProducts() {
        viewModelScope.launch {
            productRepository.fetchAndSaveProducts()
        }
    }
    fun toggleFavorite(productId: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            productRepository.updateFavorite(productId, isFavorite)
            refreshProducts() // Veritabanı güncellenince tüm ürünleri tekrar yükle
        }
    }

}