package com.bpn.trainme.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String, val arguments: List<NamedNavArgument> = emptyList() ) {

    data object Home : Screen("home")

    data object ProductDetail : Screen(
        route = "detail/{productId}",
        arguments = listOf(
            navArgument("productId") {
                type = NavType.IntType
                nullable = false
            }
        )
    ){
        fun createRoute(productId: Int) : String = "detail/$productId"
    }

    //Add more screens as app grows
}