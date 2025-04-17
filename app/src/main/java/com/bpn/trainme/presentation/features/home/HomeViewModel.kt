package com.bpn.trainme.presentation.features.home

import androidx.paging.PagingData
import com.bpn.trainme.domain.model.Exercise
import com.bpn.trainme.domain.usecase.DeleteAllExercisesUseCase
import com.bpn.trainme.domain.usecase.GetExerciseUseCase
import com.bpn.trainme.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getExerciseUseCase: GetExerciseUseCase,
    private val deleteAllExercisesUseCase: DeleteAllExercisesUseCase
) : BaseViewModel<HomeState, HomeUiEvent>() {

    val exercises : Flow<PagingData<Exercise>> = getExerciseUseCase()

    fun onPullToRefresh(){
        launchInViewModelScope {
            deleteAllExercisesUseCase()
        }
    }
}