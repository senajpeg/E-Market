package com.senaaksoy.smartshop.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senaaksoy.smartshop.R
import com.senaaksoy.smartshop.components.CustomBottomBar
import com.senaaksoy.smartshop.components.CustomTopBar
import com.senaaksoy.smartshop.viewModel.HomeViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.senaaksoy.smartshop.apiService.ProductDto
import com.senaaksoy.smartshop.roomDb.ProductEntity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeviewModel: HomeViewModel = hiltViewModel(),
    onAddtoCartClick: () -> Unit = {},
    onNavItemClick: (String) -> Unit = {},
    onCardClick: (Int) -> Unit = {}
) {
    val products by homeviewModel.products.observeAsState(initial = emptyList())

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
                value = "",
                onValueChange = {},
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
                items(products) { product ->
                    ProductCard(
                        product = product,
                        imageUrl = product.imageUrl,
                        price = product.price,
                        onAddtoCartClick = { onAddtoCartClick() },
                        onCardClick = { onCardClick(product.id) }
                    )
                }
            }
        }
    }
}


@Composable
fun ProductCard(modifier: Modifier = Modifier,
                product: ProductEntity,
                imageUrl:String,
                price:Double,
                onAddtoCartClick: () -> Unit,
                onCardClick: (Int) -> Unit) {
        Surface(
            modifier = modifier
                .shadow(elevation = 2.dp)
                .background(color = Color.White)
                .clickable { onCardClick(product.id) },
            shape = RectangleShape,
            border = BorderStroke(width = 1.dp, color = Color.LightGray)

        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = modifier
                        .fillMaxWidth()
                )

                /*Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = Color(0xFFF1F1F1),
                    modifier = modifier
                        .clickable {}
                        .size(24.dp)
                        .align(alignment = Alignment.End)
                )*/


                Text(
                    text = product.name,
                    fontSize = 16.sp,
                    color = Color(0xFF2054fc),
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    modifier = modifier.fillMaxWidth()
                )
                Text(
                    text = "${product.price}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Button(
                    modifier = modifier.fillMaxWidth(),
                    onClick = { onAddtoCartClick() },
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2054fc))

                ) {
                    Text(text = stringResource(id = R.string.add_to_cart))
                }
            }


        }



}


@Preview(showBackground = true)
@Composable
fun Ahmet() {
    HomeScreen()
}