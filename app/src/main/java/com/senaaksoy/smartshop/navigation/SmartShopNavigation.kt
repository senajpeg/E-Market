package com.senaaksoy.smartshop.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.senaaksoy.smartshop.screens.CartScreen
import com.senaaksoy.smartshop.screens.DetailScreen
import com.senaaksoy.smartshop.screens.FavouritesScreen
import com.senaaksoy.smartshop.screens.HomeScreen
import com.senaaksoy.smartshop.viewModel.HomeViewModel

@Composable
fun SmartShopNavigation() {
    val navController = rememberNavController()
    val viewModel: HomeViewModel = hiltViewModel()
    val products by viewModel.products.observeAsState(initial = emptyList())


    NavHost(
        navController = navController,
        startDestination = "${Screen.HOMESCREEN.route}/{productid}"
    ) {
        composable("${Screen.HOMESCREEN.route}/{productid}") {
            HomeScreen(
                navController = navController,
                products = products
            )
        }
        composable(
            route = "${Screen.DETAILSCREEN.route}/{productid}",
            arguments = listOf(navArgument("productid") { type = NavType.IntType })
        )
        { backStackEntry ->
            val productid = backStackEntry.arguments?.getInt("productid")
            DetailScreen(
                productId = productid,
                products = products,
                onBackPressed = { navController.popBackStack() },
                navController = navController
            )
        }
        composable(
            route = "${Screen.CARTSCREEN.route}/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            CartScreen(
                productId = productId,
                products = products,
                navController = navController,
                onBackPressed = { navController.popBackStack() }
            )
        }
        composable(
            "${Screen.FAVOURITESSCREEN.route}/{productId}",
            arguments = listOf(navArgument("productId"){type=NavType.IntType})
            ) {backStackEntry ->
            val productId=backStackEntry.arguments?.getInt("productId")
            FavouritesScreen(
                productId = productId,
                navController = navController,
                products = products,
                onBackPressed = {navController.popBackStack() }
            )

        }
        composable("${Screen.PROFILESCREEN.route}/{productId}") {
            // Profil ekranı
        }


    }
}