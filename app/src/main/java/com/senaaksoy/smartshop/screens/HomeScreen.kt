package com.senaaksoy.smartshop.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senaaksoy.smartshop.R
import com.senaaksoy.smartshop.components.CustomBottomBar
import com.senaaksoy.smartshop.components.CustomTopBar
import com.senaaksoy.smartshop.viewModel.HomeViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.senaaksoy.smartshop.navigation.Screen
import com.senaaksoy.smartshop.roomDb.ProductEntity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    homeviewModel: HomeViewModel = hiltViewModel(),
    onNavItemClick: (String) -> Unit = {},
    products : List<ProductEntity>

) {
    var searchQuery by remember { mutableStateOf("") }

    val filteredList= products.filter { product->
        product.name.contains(searchQuery,ignoreCase = true)
    }
    LaunchedEffect(Unit) {
        homeviewModel.refreshProducts()
    }

    Scaffold(
        topBar = {
            CustomTopBar(title = stringResource(id = R.string.e_market))
        },
        bottomBar = {
            CustomBottomBar(onNavItemClick = onNavItemClick)
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TextField(
                value = searchQuery,
                onValueChange = {searchQuery=it},
                label = { Text(text = stringResource(id = R.string.search)) },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(12.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(12.dp)
            ) {
                items(filteredList) { product ->
                    ProductCard(
                        product = product,
                        imageUrl = product.imageUrl,
                        price = product.price,
                        navController = navController,
                        productid = product.id,
                        homeViewModel = homeviewModel
                    )
                }
            }
        }
    }
}


@Composable
fun ProductCard(modifier: Modifier = Modifier,
                product: ProductEntity,
                productid :Int,
                navController: NavController,
                imageUrl:String,
                price:Double,
                homeViewModel: HomeViewModel) {
        Surface(
            modifier = modifier
                .shadow(elevation = 2.dp)
                .background(color = Color.White)
                .clickable {
                    navController.navigate("${Screen.DETAILSCREEN.route}/${productid}")
                },
            shape = RectangleShape,
            border = BorderStroke(width = 1.dp, color = Color.LightGray)

        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Box {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                        modifier = modifier
                            .fillMaxWidth()
                    )
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = if (product.isFavorite) Color.Yellow else Color(0xFFF1F1F1),
                        modifier = modifier
                            .padding(4.dp)
                            .clickable {homeViewModel.toggleFavorite(productid, !product.isFavorite)}
                            .height(24.dp)
                            .align(alignment = Alignment.TopEnd)
                    )
                }



                Text(
                    text = product.name,
                    fontSize = 16.sp,
                    color = Color(0xFF2054fc),
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    modifier = modifier.fillMaxWidth()
                )
                Text(
                    text = "${product.price} Tl",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Button(
                    modifier = modifier.fillMaxWidth(),
                    onClick = { navController.navigate("${Screen.CARTSCREEN.route}/${productid}") },
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2054fc))

                ) {
                    Text(text = stringResource(id = R.string.add_to_cart))
                }
            }


        }



}


