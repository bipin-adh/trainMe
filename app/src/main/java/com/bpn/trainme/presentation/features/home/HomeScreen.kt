package com.bpn.trainme.presentation.features.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bpn.trainme.domain.model.Product
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
    ) {

    val uiState = viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is HomeEvent.ShowError -> {
                    // show error

                }
            }
        }
    }

    uiState.value?.let { state->
        when {
            state.isLoading -> {
                // show loading
                CircularProgressIndicator(modifier = Modifier.fillMaxSize())
            }

            state.errorMsg != null ->{
                Text(
                    text = state.errorMsg,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            state.products.isNotEmpty() -> {
                // show products
                LazyColumn {
                    items(state.products) { product ->
                        ProductItem(
                            product = product,
                            onClick = {
                                viewModel.processIntent(HomeIntent.ProductClicked(product.id))
                            }
                        )
                    }
                }
            }

            else -> {
                // show empty
                Text(
                    text = "No products found",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxSize().padding(16.dp)
                )
            }
        }
    }?: run {
        // show loading
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun ProductItem(product: Product, onClick: () -> Unit) {
    Text(
        text = product.title,
        modifier = Modifier.clickable(onClick = onClick)
            .padding(16.dp)
    )
}
