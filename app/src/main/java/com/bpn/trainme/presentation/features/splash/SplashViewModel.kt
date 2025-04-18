package com.bpn.trainme.presentation.features.splash

import com.bpn.trainme.presentation.BaseViewModel
import com.bpn.trainme.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(

) : BaseViewModel<SplashState, SplashUiEvent>() {

    init {
        updateState(UiState.Idle)
        startSplashTimer()
    }

    private fun startSplashTimer() {
        launchInViewModelScope {
            delay(2000)
            emitEvent(SplashUiEvent.NavigateToLogin)
        }
    }
}
