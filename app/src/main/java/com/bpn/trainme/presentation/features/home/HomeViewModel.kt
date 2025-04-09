package com.bpn.trainme.presentation.features.home


import androidx.lifecycle.viewModelScope
import com.bpn.trainme.domain.model.Product
import com.bpn.trainme.domain.usecase.GetProductListUseCase
import com.bpn.trainme.presentation.BaseViewModel
import com.bpn.trainme.presentation.navigation.NavigationManager
import com.bpn.trainme.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase,
    private val navigationManager: NavigationManager
) : BaseViewModel<HomeUiState, HomeEvent>() {

   init {
       setInitialState(HomeUiState())
       processIntent(HomeIntent.LoadProducts)
   }

    override fun createErrorEvent(throwable: Throwable): HomeEvent {
        return HomeEvent.ShowError(throwable.message ?: "Unknown error")
    }

    fun processIntent(intent: HomeIntent){

        when(intent){
            is HomeIntent.LoadProducts -> loadProducts()
            is HomeIntent.ProductClicked -> viewModelScope.launch {
                navigationManager.navigate(
                    NavigationManager.NavigationEvent.NavigateTo(
                        route = Screen.ProductDetail.createRoute(
                            intent.productId
                        )
                    )
                )
            }

            // future intents like refresh filter here
        }
    }

    private fun loadProducts(){
        launch {
            handleApiCall(
                call = { getProductListUseCase() },
                onLoading = {isLoading -> updateState { it.copy(isLoading = isLoading) }},
                onSuccess = { products -> updateState { it.copy(products = products) }}
            )
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
    data class  ShowError(val errorMsg: String): HomeEvent()
}