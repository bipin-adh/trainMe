package com.bpn.trainme.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : Any, Event : Any> : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<State>>(UiState.Idle)
    val uiState: StateFlow<UiState<State>> = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    // update state safely
    protected fun updateState(newState: UiState<State>) {
        _uiState.value = newState
    }

    //safe launch with error handling
    protected fun launchInViewModelScope(
        onError: (Throwable) -> Unit = {
            _uiState.value = UiState.Error(it.message ?: "Unknown error")
        },
        block: suspend CoroutineScope.() -> Unit
    ) {
        val handler = CoroutineExceptionHandler { _, throwable ->
            onError(throwable)
        }

        viewModelScope.launch(handler) {
            block()
        }
    }

    //generic api call handler
    // Generic API call handler
    protected fun <T> handleApiCall(
        call: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit = {
            _uiState.value = UiState.Error(it.message ?: "Unknown error")
        }
    ) {
        launchInViewModelScope(onError) {
            try {
                updateState(UiState.Loading)
                val result = call()
                onSuccess(result)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    protected fun emitEvent(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }
}

sealed interface UiState<out T> {
    data object Idle : UiState<Nothing>
    data object Loading : UiState<Nothing>
    data class Success<out T>(val data: T) : UiState<T>
    data class Error(val message: String) : UiState<Nothing>
}
