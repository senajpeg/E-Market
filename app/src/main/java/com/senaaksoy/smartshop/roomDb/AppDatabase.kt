package com.senaaksoy.smartshop.roomDb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class, CartEntity::class,FavoriteEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract  fun productDao(): ProductDao
    abstract  fun cartDao(): CartDao
    abstract fun favoriteDao(): FavoriteDao


}