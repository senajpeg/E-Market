package com.senaaksoy.smartshop.apiService

import com.senaaksoy.smartshop.roomDb.ProductEntity
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<ProductDto>
}

data class ProductDto(
    val id: String,
    val name: String,
    val price: String,
    val description: String,
    val image: String
) {
    fun toEntity() = ProductEntity(
        id=id.toInt(),
        name=name,
        price=price.toDouble(),
        description=description,
        imageUrl=image)
}
