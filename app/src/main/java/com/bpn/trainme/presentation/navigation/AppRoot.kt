package com.bpn.trainme.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.bpn.trainme.presentation.features.home.homeScreen
import com.bpn.trainme.presentation.features.login.loginScreen
import com.bpn.trainme.presentation.features.register.registerScreen
import com.bpn.trainme.presentation.features.splash.SplashDestination
import com.bpn.trainme.presentation.features.splash.splashScreen

@Composable
fun AppRoot(navigationManager: NavigationManager, navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = SplashDestination
    ){
        splashScreen(
            onNavigateToLogin = {
                navigationManager.navigateToLogin()
            },
            onNavigateToHome = {
                navigationManager.navigateToHome()
            }
        )

        loginScreen(
            onNavigateToMain = {
                navigationManager.navigateToHome()
            },
            onNavigateToRegister = {
                navigationManager.navigateToRegister()
            }
        )

        registerScreen(
            onNavigateToMain = {
                navigationManager.navigateToHome()
            },
            onNavigateToLogin = {
                navigationManager.navigateToLogin()
            }
        )

        homeScreen(
            onNavigateToSettings = {
                navigationManager.navigateToLogin()
            }
        )
    }
}