package com.senaaksoy.smartshop.roomDb

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "products")
data class ProductEntity (
    @PrimaryKey val id : Int,
    val name : String,
    val price : Double,
    val description : String,
    val imageUrl : String,
    val isFavorite: Boolean=false,

)
@Entity(tableName = "cart")
data class CartEntity (
    @PrimaryKey val id : Int,
    val name : String,
    val price : Double,
    val quantity : Int
)
