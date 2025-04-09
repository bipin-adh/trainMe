package com.bpn.trainme.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State: Any , Event : Any> : ViewModel() {

    private val _uiState = MutableStateFlow<State?>(null)
    val uiState: StateFlow<State?> = _uiState.asStateFlow()

    private val _eventFlow = Channel<Event>(Channel.BUFFERED)
    val eventFlow = _eventFlow.receiveAsFlow()

    // initialize state in subclass
    protected fun setInitialState(initialState : State){
        _uiState.value = initialState
    }

    // update state safely
    protected fun updateState(transform : (State) -> State){
        _uiState.value?.let { currentState->
            _uiState.value = transform(currentState)
        }
    }

    // send events eg: error , toasts
    protected fun sendEvent(event : Event){
        viewModelScope.launch {
            _eventFlow.send(event)
        }
    }

    //safe launch with error handling
    protected fun launch(
        onError : (Throwable) -> Unit = { sendEvent(createErrorEvent(it)) },
        block : suspend () -> Unit
    ){
        val handler = CoroutineExceptionHandler{_, throwable ->
            onError(throwable)
        }

        viewModelScope.launch(handler) {
            block()
        }
    }

    //abstract method for subclass to define error events
    protected abstract fun createErrorEvent(throwable: Throwable) : Event


    //generic api call handler
    protected suspend fun <T : Any> handleApiCall(
        call: suspend () -> T,
        onLoading : (Boolean) -> Unit = {},
        onSuccess : (T) -> Unit,
        onError : (Throwable) -> Unit = { sendEvent(createErrorEvent(it)) },
    ) {
        try {
            onLoading(true)
            val result = call()
            onLoading(false)
            onSuccess(result)
        }catch (e: Exception){
            onLoading(false)
            onError(e)
        }
    }
}