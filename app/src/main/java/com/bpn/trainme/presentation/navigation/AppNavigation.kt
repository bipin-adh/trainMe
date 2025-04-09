package com.bpn.trainme.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bpn.trainme.presentation.features.home.HomeScreen
import com.bpn.trainme.presentation.features.product_detail.ProductDetailScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route){
            HomeScreen( onNavigateToDetail = { productId->
                navController.navigate("${Screen.ProductDetail.route}/$productId")
            })
        }

        composable(
            route = "${Screen.ProductDetail.route}/{productId}",
            arguments = listOf(
                navArgument("productId"){
                    type = NavType.IntType
                }
            )
        ){ backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            ProductDetailScreen(productId = productId)
        }
    }
}

sealed class Screen(val route: String){
    data object Home: Screen("home")
    data object ProductDetail : Screen("detail")
}