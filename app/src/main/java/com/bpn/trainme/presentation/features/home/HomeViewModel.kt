package com.bpn.trainme.presentation.features.home

import androidx.paging.PagingData
import com.bpn.trainme.domain.model.Exercise
import com.bpn.trainme.domain.usecase.GetExerciseUseCase
import com.bpn.trainme.presentation.BaseViewModel
import com.bpn.trainme.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getExerciseUseCase: GetExerciseUseCase
) : BaseViewModel<HomeState, HomeUiEvent>() {

    val exercises : Flow<PagingData<Exercise>> = getExerciseUseCase()

    fun onPullToRefresh(){
        launchInViewModelScope {
            getExerciseUseCase()
        }
    }
//    init {
//        fetchExercises()
//    }
//
//    fun fetchExercises(offset: Int = 0){
//        handleApiCall(
//            call = { getExerciseUseCase(offset) },
//            onSuccess = { exercises ->
//                updateState(UiState.Success(
//                    HomeState(
//                        exercises = exercises
//                    )
//                ))
////                emitEvent(HomeUiEvent.NavigateToRegister)
//            },
//            onError = { throwable -> updateState(UiState.Error(throwable.message ?: "Unknown error")) }
//        )
//    }
//    fun getProducts(){
//        handleApiCall(
//            call = { getExerciseUseCase() },
//            onSuccess = { products ->
//                updateState(UiState.Success(
//                    HomeState(
//                        products = products
//                    )
//                ))
////                emitEvent(HomeUiEvent.NavigateToRegister)
//            },
//            onError = { throwable -> updateState(UiState.Error(throwable.message ?: "Unknown error")) }
//        )
//    }
}