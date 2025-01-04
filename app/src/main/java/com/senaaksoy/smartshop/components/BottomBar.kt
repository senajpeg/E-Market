package com.senaaksoy.smartshop.components

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.senaaksoy.smartshop.R
import com.senaaksoy.smartshop.navigation.Screen

data class NavItem (
    @DrawableRes val icon : Int,
    val screen : String

)

val navItem = listOf(
    NavItem(
        icon = R.drawable.home,
        screen = Screen.HOMESCREEN.route
    ),
    NavItem(
        icon = R.drawable.shopping_bag,
        screen = Screen.CARTSCREEN.route
    ),
    NavItem(
        icon = R.drawable.star,
        screen = Screen.FAVOURITESSCREEN.route
    ),
    NavItem(
        icon = R.drawable.user,
        screen = Screen.PROFILESCREEN.route
    )

)


@Composable
fun CustomBottomBar(navController : NavController) {
    var currentScreen by remember {mutableStateOf("${Screen.HOMESCREEN.route}/0") }
    NavigationBar(containerColor = Color.White) {
        navItem.forEach { item ->
            NavigationBarItem(
                selected = currentScreen == item.screen,
                onClick = {
                        navController.navigate("${item.screen}/0")
                        currentScreen=item.screen

                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = null,
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.LightGray
                )
            )
        }
    }
}
