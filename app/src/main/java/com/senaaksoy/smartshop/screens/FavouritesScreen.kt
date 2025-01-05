package com.senaaksoy.smartshop.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.senaaksoy.smartshop.R
import com.senaaksoy.smartshop.components.CustomBottomBar
import com.senaaksoy.smartshop.components.CustomTopBar
import com.senaaksoy.smartshop.roomDb.FavoriteEntity
import com.senaaksoy.smartshop.roomDb.ProductEntity
import com.senaaksoy.smartshop.viewModel.FavoriteViewModel
import com.senaaksoy.smartshop.viewModel.HomeViewModel

@Composable
fun FavouritesScreen(
    modifier: Modifier=Modifier,
    productId: Int?,
    onBackPressed: () -> Unit,
    viewModel: FavoriteViewModel = hiltViewModel(),
    navController:NavController,
    products: List<ProductEntity>
) {

    val favoriteProducts by viewModel.favoriteProducts.observeAsState(emptyList())

    // LaunchedEffect ile favoriye ürün ekleme işlemini yapalım
    LaunchedEffect(productId) {
        productId?.let { id ->
            val selectedProduct = products.find { it.id == id }
            selectedProduct?.let {
                // Eğer ürün favorilerde değilse, favorilere ekliyoruz
                if (favoriteProducts.none { it.productId == selectedProduct.id }) {
                    val favoriteEntity = FavoriteEntity(
                        productId = it.id,
                        name = it.name,
                        description = it.description,
                        price = it.price,
                        imageUrl = it.imageUrl
                    )
                    // ViewModel üzerinden favorilere ekliyoruz
                    viewModel.addToFavorites(favoriteEntity)
                }
            }
        }
    }


    Scaffold(
        topBar = { CustomTopBar(
            title = stringResource(id = R.string.favorites),
            showBackIcon = true,
            onBackPressed = onBackPressed)
        },
        bottomBar = { CustomBottomBar(navController = navController)})
    {  innerPadding->
        Column(modifier = modifier
            .fillMaxSize()
            .padding(innerPadding))
        {
            if(favoriteProducts.isNotEmpty()){
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(favoriteProducts){favoriteProduct->
                    Card(modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)) {
                        Box {
                            AsyncImage(
                                model = favoriteProduct.imageUrl,
                                contentDescription = null,
                                modifier = modifier.fillMaxWidth()
                            )
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = null,
                                tint = Color.Gray,
                                modifier = modifier
                                    .padding(4.dp)
                                    .clickable {

                                    }
                                    .align(Alignment.TopEnd)
                                    .height(36.dp)
                            )
                        }

                        Column(modifier = modifier.padding(8.dp)) {
                            Text(
                                text = favoriteProduct.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            Text(
                                text = favoriteProduct.description,
                                fontSize = 14.sp,
                                modifier = modifier.padding(top = 4.dp)
                            )
                            Text(
                                text = "${favoriteProduct.price} TL",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                modifier = modifier.padding(top = 8.dp)
                            )
                        }

                    }

                }

            }
        }
            else{
                Column( modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Text(
                        text = stringResource(id = R.string.information),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = modifier.padding(16.dp)
                    )

                }
            }



        }


    }












}