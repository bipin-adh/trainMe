package com.bpn.trainme.presentation.features.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable


@Serializable
data object LoginDestination

fun NavGraphBuilder.loginScreen(
    onNavigateToMain: () -> Unit,
    onNavigateToRegister: () -> Unit
){
    composable<LoginDestination>{
        LoginScreen(
            onNavigateToMain = onNavigateToMain,
            onNavigateToRegister = onNavigateToRegister
        )
    }
}


data class LoginState(
    val email: String = "",
    val password: String = ""
)

sealed interface LoginUiEvent{
    data class NavigateToHome(val id: String): LoginUiEvent
    data object NavigateToRegister: LoginUiEvent
    data object NavigateBack: LoginUiEvent
}
