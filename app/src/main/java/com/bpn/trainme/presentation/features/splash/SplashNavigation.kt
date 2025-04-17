package com.bpn.trainme.presentation.features.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable


@Serializable
data object SplashDestination

fun NavGraphBuilder.splashScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit
){
    composable<SplashDestination>{
        SplashScreen(
            onNavigateToLogin = onNavigateToLogin,
            onNavigateToHome = onNavigateToHome
        )
    }
}


data class SplashState(
    val email: String = "",
    val password: String = "",
    val isLoggedIn: Boolean = false
)

sealed interface SplashUiEvent{
    data object NavigateToHome: SplashUiEvent
    data object NavigateBack: SplashUiEvent
    data object NavigateToRegister: SplashUiEvent
    data object NavigateToLogin: SplashUiEvent
}

