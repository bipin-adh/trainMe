package com.bpn.trainme.presentation.features.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onNavigateToMain: () -> Unit,
    onNavigateToRegister: () -> Unit
){
    val viewModel = hiltViewModel<LoginViewModel>()
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event->
            when(event){
                is LoginUiEvent.NavigateToHome -> onNavigateToMain()
                is LoginUiEvent.NavigateToRegister -> onNavigateToRegister()
                is LoginUiEvent.NavigateBack -> {

                }
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Login")
                }
            )
        }
    ) { paddingValues->
        Box( modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            Column {
                Button(onClick = onNavigateToMain) {
                    Text(text = "Login")
                }

                Button(onClick = onNavigateToRegister) {
                    Text(text = "Let's SignUp")
                }
            }
        }
    }
}
