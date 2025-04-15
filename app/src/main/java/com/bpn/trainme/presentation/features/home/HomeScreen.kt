package com.bpn.trainme.presentation.features.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bpn.trainme.domain.model.Exercise
import com.bpn.trainme.presentation.UiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToSettings: () -> Unit
){
    val viewModel = hiltViewModel<HomeViewModel>()
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event->
            when(event){
                is HomeUiEvent.NavigateToSettings -> onNavigateToSettings()
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Home")
                }
            )
        }
    ) { paddingValues->
        when(val state = uiState.value) {
            is UiState.Idle -> Text("Readty to load")
            is UiState.Loading -> CircularProgressIndicator()
            is UiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(paddingValues = paddingValues),
                    contentAlignment = Alignment.Center) {
                    Text(text = state.message)
                }
            }
            is UiState.Success ->
                Box( modifier = Modifier.fillMaxSize().padding(paddingValues = paddingValues)) {
                    ExerciseListContent(
                        exercises = state.data.exercises ?: emptyList(),
                        onExerciseClick = { id ->
                            // Handle product click event
                        }
                    )
                }
        }
    }
}

@Composable
fun ExerciseListContent(
    exercises: List<Exercise>,
    onExerciseClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(exercises) { exercise ->
            ExerciseItem(
                exercise = exercise,
                onClick = { onExerciseClick(exercise.id) }
            )
        }
    }
}


@Composable
fun ExerciseItem(exercise : Exercise, onClick: () -> Unit) {
    Text(
        text = exercise.name,
        modifier = Modifier.clickable(onClick = onClick)
            .padding(16.dp)
    )
}

