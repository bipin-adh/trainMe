package com.bpn.trainme.presentation.features.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bpn.trainme.domain.model.Exercise
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
    val exercises: List<Exercise>? = null
)

sealed interface HomeUiEvent {
    data object NavigateToSettings : HomeUiEvent
}
