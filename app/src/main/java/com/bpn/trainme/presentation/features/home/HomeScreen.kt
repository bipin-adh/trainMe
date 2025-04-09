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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bpn.trainme.domain.model.Product
import com.bpn.trainme.presentation.features.home.HomeViewModel.HomeIntent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToDetail: (Int) -> Unit
    ) {

    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is HomeViewModel.HomeEvent.NavigateToDetail -> {
                    // navigate to detail screen
                    onNavigateToDetail(event.productId)
                }

                is HomeViewModel.HomeEvent.ShowError -> {
                    // show error
                }
            }
        }
    }

    if(uiState.value.isLoading){
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    }else{
        LazyColumn {
            items(uiState.value.products) { product ->
                ProductItem(
                    product = product,
                    onClick = {
                        viewModel.processIntent(HomeIntent.ProductClicked(product.id))
                    }
                )
            }
        }
    }

    uiState.value.errorMsg?.let {error->
        Text(
            text = error,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(16.dp)
        )
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
