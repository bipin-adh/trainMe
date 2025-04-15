package com.bpn.trainme.presentation.features.home

import com.bpn.trainme.domain.usecase.GetProductListUseCase
import com.bpn.trainme.presentation.BaseViewModel
import com.bpn.trainme.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase,
) : BaseViewModel<HomeState, HomeUiEvent>() {

    init {
        getProducts()
    }

    fun getProducts(){
        handleApiCall(
            call = { getProductListUseCase() },
            onSuccess = { products ->
                updateState(UiState.Success(
                    HomeState(
                        products = products
                    )
                ))
//                emitEvent(HomeUiEvent.NavigateToRegister)
            },
            onError = { throwable -> updateState(UiState.Error(throwable.message ?: "Unknown error")) }
        )
    }
}