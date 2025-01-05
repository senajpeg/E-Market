package com.senaaksoy.smartshop.repository

import androidx.lifecycle.LiveData
import com.senaaksoy.smartshop.roomDb.FavoriteDao
import com.senaaksoy.smartshop.roomDb.FavoriteEntity
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val favoriteDao: FavoriteDao
) {


    fun getFavoriteProducts(): LiveData<List<FavoriteEntity>> = favoriteDao.getAllFavorites()


    suspend fun addFavorite(favorite: FavoriteEntity) {
        favoriteDao.addFavorite(favorite)
    }

    suspend fun removeFavorite(productId: Int) {
        favoriteDao.removeFavorite(productId)
    }
}
