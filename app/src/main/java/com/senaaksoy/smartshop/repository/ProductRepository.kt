package com.senaaksoy.smartshop.repository

import androidx.lifecycle.LiveData
import com.senaaksoy.smartshop.apiService.ApiService
import com.senaaksoy.smartshop.roomDb.ProductDao
import com.senaaksoy.smartshop.roomDb.ProductEntity
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productDao: ProductDao,
    private val apiService: ApiService
) {


    val allProducts: LiveData<List<ProductEntity>> = productDao.getAllProducts()

    fun getProductById(productId: Int): ProductEntity? {
        return productDao.getProductById(productId)
    }
    suspend fun fetchAndSaveProducts() {
        val response = apiService.getProducts()
        productDao.insertAll(response.map { it.toEntity() })
    }


}