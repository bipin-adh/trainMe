package com.bpn.trainme.presentation.features.product_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.bpn.trainme.domain.model.Product
import com.bpn.trainme.domain.usecase.GetProductDetailUseCase
import com.bpn.trainme.presentation.BaseViewModel
import com.bpn.trainme.presentation.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val navigationManager: NavigationManager,
    private val getProductDetailUseCase: GetProductDetailUseCase,
): BaseViewModel<ProductDetailUiState, ProductDetailEvent>() {

    val productId: Int = savedStateHandle["productId"] ?: 0

    init {
        setInitialState(ProductDetailUiState())
        processIntent(ProductDetailIntent.LoadProduct(productId))
        loadProduct(productId = productId)
    }

    override fun createErrorEvent(throwable: Throwable): ProductDetailEvent {
        return ProductDetailEvent.ShowError(throwable.message ?: "Unknown error")
    }

    fun processIntent(intent: ProductDetailIntent){
        when(intent){
            is ProductDetailIntent.LoadProduct -> loadProduct(intent.productId)
            is ProductDetailIntent.NavigateBack -> viewModelScope.launch {
                navigationManager.navigate(NavigationManager.NavigationEvent.PopBackStack)
            }
        }
    }

    private fun loadProduct(productId: Int){
        launch {
            handleApiCall(
                call = { getProductDetailUseCase(productId) },
                onLoading = { isLoading -> updateState { it.copy(isLoading = isLoading) } },
                onSuccess = { product -> updateState { it.copy(product = product) } }
            )
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
    data object NavigateBack: ProductDetailIntent()
}

sealed class ProductDetailEvent{
    data class ShowError(val errorMsg: String): ProductDetailEvent()
}