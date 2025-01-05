package com.senaaksoy.smartshop.module

import android.content.Context
import androidx.room.Room
import com.senaaksoy.smartshop.apiService.ApiService
import com.senaaksoy.smartshop.roomDb.AppDatabase
import com.senaaksoy.smartshop.roomDb.CartDao
import com.senaaksoy.smartshop.roomDb.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "smart_shop_db").build()
    }

    @Provides
    fun provideProductDao(db: AppDatabase): ProductDao = db.productDao()

    @Provides
    fun provideCartDao(db: AppDatabase): CartDao = db.cartDao()


    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://5fc9346b2af77700165ae514.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}
