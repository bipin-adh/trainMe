package com.bpn.trainme.presentation.features.product_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpn.trainme.domain.model.Product
import com.bpn.trainme.domain.usecase.GetProductDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState = _uiState.asStateFlow()

    private val _eventFlow = Channel<ProductDetailEvent>(Channel.BUFFERED)
    val eventFlow = _eventFlow.receiveAsFlow()

    fun processIntent(intent: ProductDetailIntent){
        when(intent){
            is ProductDetailIntent.LoadProduct -> loadProduct(intent.productId)
        }
    }

    private fun loadProduct(productId: Int){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val product = getProductDetailUseCase.invoke(productId)
                _uiState.value = _uiState.value.copy(product = product, isLoading = false)
            }catch (e: Exception){
                _uiState.value = _uiState.value.copy(errorMsg = e.message, isLoading = false)
                _eventFlow.send(ProductDetailEvent.ShowError(e.message ?: "Unknown error"))
            }
        }
    }
}

data class ProductDetailUiState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val errorMsg: String? = null
)

sealed class ProductDetailIntent{
    data class LoadProduct(val productId: Int): ProductDetailIntent()
}

sealed class ProductDetailEvent{
    data class ShowError(val errorMsg: String): ProductDetailEvent()
}