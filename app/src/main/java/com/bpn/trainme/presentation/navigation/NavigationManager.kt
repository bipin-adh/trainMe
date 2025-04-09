package com.bpn.trainme.presentation.navigation

import androidx.navigation.NavController
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class NavigationManager {
    private val _navigationEvents = Channel<NavigationEvent>(Channel.BUFFERED)
    val navigationEvents = _navigationEvents.receiveAsFlow()

    fun navigate(event: NavigationEvent){
        _navigationEvents.trySend(event)
    }

    sealed class NavigationEvent{
        data class NavigateTo(val route: String): NavigationEvent()
        data object PopBackStack: NavigationEvent()

        //add more events as needed like navigateup and clearbackstack
    }
}

fun NavController.handleNavigationEvents(event: NavigationManager.NavigationEvent){
    when(event){
        is NavigationManager.NavigationEvent.NavigateTo -> navigate(event.route)
        is NavigationManager.NavigationEvent.PopBackStack -> popBackStack()
    }
}

