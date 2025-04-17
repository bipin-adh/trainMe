package com.bpn.trainme.presentation.features.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.bpn.trainme.domain.model.Exercise
import com.bpn.trainme.presentation.UiState
import kotlinx.coroutines.launch
import timber.log.Timber


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToSettings: () -> Unit
){
    val viewModel = hiltViewModel<HomeViewModel>()

    val scope = rememberCoroutineScope()
    val uiState = viewModel.uiState.collectAsState()

    val exercises = viewModel.exercises.collectAsLazyPagingItems()

    val isRefreshing = exercises.loadState.refresh is LoadState.Loading
    val pullToRefreshState = rememberPullToRefreshState()

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

        PullToRefreshBox(
            isRefreshing = isRefreshing,
            state = pullToRefreshState,
            onRefresh = {
                scope.launch {
                    exercises.refresh()
//                    viewModel.onPullToRefresh()
                }
            },
            indicator = {
                Indicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                    isRefreshing = isRefreshing,
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    state = pullToRefreshState
                )
            },
        ){
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(paddingValues)
            ) {
                items(exercises.itemCount) { index ->
                    val exercise = exercises[index]
                    exercise?.let {
                        ExerciseItem(exercise = exercise,
                            onClick = {

                            })
                    }
                }

                // Optional: loading & error states
                when (val state = exercises.loadState.append) {
                    is LoadState.Loading -> item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                    is LoadState.Error -> item {
                        Text("Error: ${state.error.message}")
                    }
                    else -> {}
                }
            }
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

