package com.senaaksoy.smartshop.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
        screen = Screen.HOMESCREEN.route
    )

)

@Composable
fun CustomBottomBar(onNavItemClick :(String)->Unit={}) {
    NavigationBar(containerColor = Color.White) {
        navItem.forEach { item ->
            NavigationBarItem(
                selected = false,
                onClick = { onNavItemClick(item.screen)},
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = null,
                    )
                },
            )
        }
    }
}
