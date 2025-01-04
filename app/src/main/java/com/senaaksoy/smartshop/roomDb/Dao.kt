package com.senaaksoy.smartshop.roomDb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun getAllProducts(): LiveData<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products : List<ProductEntity>)
    @Query("UPDATE products SET isFavorite = :isFavorite WHERE id = :productId")
    suspend fun updateFavorite(productId: Int, isFavorite: Boolean)


}
@Dao
interface CartDao {

    @Query("SELECT * FROM cart")
    fun getCartItems(): LiveData<List<CartEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItem : CartEntity)

    @Query("DELETE FROM cart WHERE id = :id")
    suspend fun deleteById(id : Int)

    @Query("UPDATE cart SET quantity = :quantity WHERE id = :id")
    suspend fun updateQuantity(id: Int, quantity: Int)

}