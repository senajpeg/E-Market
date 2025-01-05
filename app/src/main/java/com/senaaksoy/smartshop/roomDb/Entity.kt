package com.senaaksoy.smartshop.roomDb

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "products")
data class ProductEntity (
    @PrimaryKey val id : Int,
    val name : String,
    val price : Double,
    val description : String,
    val imageUrl : String

)
@Entity(tableName = "cart")
data class CartEntity (
    @PrimaryKey val id : Int,
    val name : String,
    val price : Double,
    val quantity : Int
)
@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productId: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String
)
