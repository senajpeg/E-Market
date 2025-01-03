package com.senaaksoy.smartshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.senaaksoy.smartshop.navigation.SmartShopNavigation
import com.senaaksoy.smartshop.screens.HomeScreen
import com.senaaksoy.smartshop.ui.theme.SmartShopTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Hilt'in bu Activity'ye bağımlılıkları enjekte etmesini sağlar
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartShopTheme {
                SmartShopNavigation()
            }
        }
    }
}
