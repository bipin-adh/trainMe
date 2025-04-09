package com.bpn.trainme.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bpn.trainme.presentation.features.home.HomeScreen
import com.bpn.trainme.presentation.features.product_detail.ProductDetailScreen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppNavigation(navigationManager: NavigationManager) {
    val navController = rememberNavController()

    // Listen to navigation events
    LaunchedEffect(Unit) {
        navigationManager.navigationEvents.collectLatest { event->
            navController.handleNavigationEvents(event = event)
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route){
            HomeScreen()
        }

        composable(
            route = Screen.ProductDetail.route,
            arguments = Screen.ProductDetail.arguments
        ){ backStackEntry ->
//            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            ProductDetailScreen()
        }
    }
}