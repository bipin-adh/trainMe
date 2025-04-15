package com.bpn.trainme.presentation.features.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bpn.trainme.domain.model.Product
import kotlinx.serialization.Serializable


@Serializable
data object HomeDestination

fun NavGraphBuilder.homeScreen(
    onNavigateToSettings: () -> Unit
) {
    composable<HomeDestination> {
        HomeScreen(
            onNavigateToSettings = onNavigateToSettings
        )
    }
}


data class HomeState(
    val products: List<Product>? = null
)

sealed interface HomeUiEvent {
    data object NavigateToSettings : HomeUiEvent
}
