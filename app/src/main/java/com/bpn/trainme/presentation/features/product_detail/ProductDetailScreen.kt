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

    if(uiState.value.isLoading){
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    }else{
        uiState.value.product?.let { product->
            Text(text = product.title, modifier = Modifier.padding(16.dp))
        }
    }

    uiState.value.errorMsg?.let { error->
        Text(
            text = error,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(16.dp)
        )
    }
}