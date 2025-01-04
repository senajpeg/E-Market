package com.senaaksoy.smartshop.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.senaaksoy.smartshop.R
import com.senaaksoy.smartshop.components.CustomBottomBar
import com.senaaksoy.smartshop.components.CustomTopBar
import com.senaaksoy.smartshop.navigation.Screen
import com.senaaksoy.smartshop.roomDb.ProductEntity

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,

    onBackPressed: () -> Unit,
    productId: Int?,
    products: List<ProductEntity>,
    navController:NavController
) {
    val productList = products.filter { id ->
        id.id == productId
    }
    val productEntity = productList.first()

    Scaffold(
        topBar = {
            CustomTopBar(
                title = productEntity.name,
                showBackIcon = true,
                onBackPressed = { onBackPressed() })
        },
        bottomBar = { CustomBottomBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .weight(1f)
            ) {
                Card(modifier = modifier.fillMaxWidth()) {
                    Box {
                        AsyncImage(
                            model = productEntity.imageUrl,
                            contentDescription = null,
                            modifier = modifier.fillMaxWidth()
                        )
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = Color(0xFFF5F2F2),
                            modifier = modifier
                                .padding(4.dp)
                                .clickable {}
                                .align(alignment = Alignment.TopEnd)
                                .height(36.dp))
                    }

                }
                Text(
                    text = productEntity.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    modifier = modifier.padding(top = 8.dp)
                )
                Text(
                    text = productEntity.description,
                    modifier = modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                )
            }


            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = modifier
                        .padding(4.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.price),
                        fontSize = 22.sp,
                        color = Color(0xFF2054fc)
                    )
                    Text(
                        text = "${productEntity.price} TL",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = modifier.padding(start = 4.dp)
                    )
                }
                Spacer(modifier = modifier.weight(0.2f))
                Button(
                    onClick = { navController.navigate("${Screen.CARTSCREEN.route}/${productId}")},
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2054fc))

                ) {
                    Text(
                        text = stringResource(id = R.string.add_to_cart),
                        fontSize = 20.sp
                    )
                }

            }


        }


    }


}




