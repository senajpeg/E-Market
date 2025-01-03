package com.senaaksoy.smartshop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.senaaksoy.smartshop.screens.CartScreen
import com.senaaksoy.smartshop.screens.DetailScreen
import com.senaaksoy.smartshop.screens.HomeScreen


@Composable
fun SmartShopNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.HOMESCREEN.route) {
        composable(Screen.HOMESCREEN.route) {
            HomeScreen(
            onAddtoCartClick = {},
            onNavItemClick = { route ->
                navController.navigate(route)
            },
            onCardClick = {productId->
                navController.navigate(Screen.DETAILSCREEN.route+ "/${productId}")}
        ) }
        composable(Screen.DETAILSCREEN.route +"/{productId}",
            arguments = listOf(navArgument(name = "productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")?:0
            DetailScreen(productId = productId)
        }

        composable(Screen.CARTSCREEN.route) {
            CartScreen(
            onNavItemClick = { route ->
                navController.navigate(route)
            }
        ) }


    }
}