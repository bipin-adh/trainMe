package com.bpn.trainme.presentation.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpn.trainme.domain.model.Product
import com.bpn.trainme.domain.usecase.GetProductListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase
) : ViewModel() {

    // UI state
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState : StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _eventFlow = Channel<HomeEvent>(Channel.BUFFERED)
    val eventFlow = _eventFlow.receiveAsFlow()

   init {
       processIntent(HomeIntent.LoadProducts)
   }

    fun processIntent(intent: HomeIntent){

        when(intent){
            is HomeIntent.LoadProducts -> loadProducts()
            is HomeIntent.ProductClicked -> viewModelScope.launch {
                _eventFlow.send(HomeEvent.NavigateToDetail(intent.productId))
            }

            // future intents like refresh filter here
        }
    }

    private fun loadProducts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                val products : List<Product> = getProductListUseCase()
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    products = products
                )
            }catch (e: Exception){
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMsg = e.message ?: "Unknown error"
                )
                _eventFlow.send(HomeEvent.ShowError(e.message ?: "Unknown error"))
            }
        }
    }

    // Ui state :persistent state for UI rendering
    data class HomeUiState(
        val isLoading: Boolean = false,
        val products: List<Product> = emptyList(), // from domain model pro
        val errorMsg: String?= null
    )

    // Intents: user actions or triggers
    sealed class HomeIntent{
        data object LoadProducts: HomeIntent()
        data class ProductClicked(val productId: Int): HomeIntent()
        // add more like refresh filter
    }

    // Events: one-time effects
    sealed class HomeEvent{
        data class NavigateToDetail(val productId: Int): HomeEvent()
        data class  ShowError(val errorMsg: String): HomeEvent()
    }
}