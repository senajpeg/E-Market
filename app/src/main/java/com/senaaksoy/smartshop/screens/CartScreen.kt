package com.senaaksoy.smartshop.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senaaksoy.smartshop.R
import com.senaaksoy.smartshop.components.CustomBottomBar
import com.senaaksoy.smartshop.components.CustomTopBar

@Composable
fun CartScreen(modifier: Modifier = Modifier,
               onNavItemClick:(String)->Unit={}) {
    val phoneList = listOf("Xiaomi Redmi Note 12", "Samsung Galaxy A54 5G", "Apple iPhone 15 Pro")
    Scaffold(
        topBar = {
            CustomTopBar(title = stringResource(id = R.string.e_market))
        },
        bottomBar = { CustomBottomBar(onNavItemClick = onNavItemClick) }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            LazyColumn {
                items(phoneList) { phone ->
                    PhoneItem(phone = phone)

                }
            }
            Spacer(modifier = Modifier.weight(1f))
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
                        text = stringResource(id = R.string.total),
                        fontSize = 22.sp,
                        color = Color(0xFF2054fc)
                    )
                    Text(
                        text = "12132 tl",
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
                        text = stringResource(id = R.string.complete),
                        fontSize = 20.sp
                    )
                }

            }

        }

    }

}

@Composable
fun PhoneItem(phone: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = phone,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            modifier = Modifier.background(Color(0x0F535252)),
            onClick = { }) {
            Icon(painter = painterResource(id = R.drawable.delete), contentDescription = null)
        }
        Text(
            text = "1",
            color = Color.White,
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(Color(0xFF2054fc))
                .size(48.dp))
        IconButton(
            modifier = Modifier.background(Color(0x0F535252)),
            onClick = { }) {
            Icon(painter = painterResource(id = R.drawable.plus), contentDescription = null)
        }


    }
}

@Preview(showBackground = true)
@Composable
fun Yilmaz() {
    CartScreen()
}