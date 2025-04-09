package com.bpn.trainme.presentation.features.product_detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProductDetailScreen(
//    productId: Int,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {

    val uiState = viewModel.uiState.collectAsState()

//    LaunchedEffect(productId) {
//        viewModel.processIntent(ProductDetailIntent.LoadProduct(productId))
//    }

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is ProductDetailEvent.ShowError -> {

               }
            }
        }
    }

    // render ui based on state
    uiState.value?.let { state->
        when{
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.fillMaxSize())
            }
            state.errorMsg != null -> {
                Text(text = state.errorMsg,color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(16.dp))
            }

            state.product != null -> {
                Text(text = state.product.title, modifier = Modifier.padding(16.dp))
            }
            else -> {
                Text(text = "No product found", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(16.dp))
            }
        }
    }?: run {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    }
}