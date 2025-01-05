package com.senaaksoy.smartshop.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senaaksoy.smartshop.repository.FavoriteRepository
import com.senaaksoy.smartshop.roomDb.FavoriteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {


    val favoriteProducts: LiveData<List<FavoriteEntity>> = favoriteRepository.getFavoriteProducts()


    fun addToFavorites(favorite: FavoriteEntity) {
        viewModelScope.launch {
            favoriteRepository.addFavorite(favorite)
        }
    }

    fun removeFromFavorites(productId: Int) {
        viewModelScope.launch {
            favoriteRepository.removeFavorite(productId)
        }
    }
}
