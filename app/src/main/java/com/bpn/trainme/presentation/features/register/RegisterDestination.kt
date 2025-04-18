package com.bpn.trainme.presentation.features.register

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object RegisterDestination

fun NavGraphBuilder.registerScreen(
    onNavigateToMain: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    composable<RegisterDestination> {
        RegisterScreen(
            onNavigateToMain = onNavigateToMain,
            onNavigateToLogin = onNavigateToLogin
        )
    }
}