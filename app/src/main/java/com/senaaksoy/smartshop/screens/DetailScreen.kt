package com.senaaksoy.smartshop.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.senaaksoy.smartshop.R
import com.senaaksoy.smartshop.components.CustomBottomBar
import com.senaaksoy.smartshop.components.CustomTopBar
import com.senaaksoy.smartshop.viewModel.HomeViewModel

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    onNavItemClick: (String) -> Unit = {},
    onBackPressed: () -> Unit = {},
    productId: Int,
    detailviewModel: HomeViewModel = hiltViewModel()
) {
    val product = detailviewModel.getProductById(productId)
    if (product == null) {
        // Burada hata mesajı gösterebilirsiniz
        Text(text = "Product not found!")
        return
    }

    Scaffold(
        topBar = {
            CustomTopBar(
                title = product.name,
                showBackIcon = true,
                onBackPressed = { onBackPressed() })
        },
        bottomBar = { CustomBottomBar(onNavItemClick = onNavItemClick) }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = null
            )

            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = Color(0xFFF5F2F2),
                modifier = modifier
                    .padding(4.dp)
                    .clickable {}
                    .align(alignment = Alignment.End)
                    .height(36.dp))
        }

        Text(
            text = product.name,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = modifier.padding(8.dp)
        )


        Text(
            text = product.description,

            modifier = modifier.padding(16.dp)
        )
        // Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = modifier
                    .padding(4.dp)
                    .weight(0.8f),
            ) {
                Text(
                    text = stringResource(id = R.string.price),
                    fontSize = 22.sp,
                    color = Color(0xFF2054fc)
                )
                Text(
                    text = "${product.price} TL",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                modifier = modifier.weight(1f),
                onClick = { },
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




