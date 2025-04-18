package com.bpn.trainme.presentation.features.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit
    ){
    val viewModel = hiltViewModel<SplashViewModel>()
    val uiState = viewModel.uiState.collectAsState()

    val trainOffset = remember { Animatable(-1000f) } // start off-screen left
    val meOffset = remember { Animatable(1000f) } // start off-screen right

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event->
            when(event){
                is SplashUiEvent.NavigateToLogin -> onNavigateToLogin()
                is SplashUiEvent.NavigateBack -> {}
                is SplashUiEvent.NavigateToHome -> {
                    onNavigateToHome()
                }
                is SplashUiEvent.NavigateToRegister -> {}
            }
        }
    }

    LaunchedEffect(Unit) {
        //move texts to center
        val moveToCenter = tween<Float>(durationMillis = 1000)
        launch {
            trainOffset.animateTo(0f, animationSpec = moveToCenter) // move to center
        }

        launch {
            meOffset.animateTo(0f, animationSpec = moveToCenter) // move to center
        }

        // After collision bounce back slightly
        delay(1000)
        val bounce = tween<Float>(durationMillis = 300)
        launch {
            trainOffset.animateTo(-50f, animationSpec = bounce) // bounce left
            trainOffset.animateTo(0f, animationSpec = bounce) // settle at center
        }

        launch {
            meOffset.animateTo(50f, animationSpec = bounce) // bounce right
            meOffset.animateTo(0f, animationSpec = bounce) // settle at center
        }

        delay(600) // wait for bounce to complete and navigate to main dashboard
        onNavigateToHome()
    }


    Box(
        modifier = Modifier.fillMaxSize().background(
//            brush = Brush.verticalGradient(
//                colors = listOf(
//                    MaterialTheme.colorScheme.primary,
//                    MaterialTheme.colorScheme.background
//                )
//            )
            color = MaterialTheme.colorScheme.background
        ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "train",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.offset(x = trainOffset.value.dp)
                )

                Text(
                    text = "Me",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.offset(x = meOffset.value.dp)
                )
            }

            Text(
                text = "Welcome to trainMe !!!" + "\n" +
                        "Your fitness buddy.",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}
