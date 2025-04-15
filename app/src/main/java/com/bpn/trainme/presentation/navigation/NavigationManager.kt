package com.bpn.trainme.presentation.navigation

import androidx.navigation.NavController
import com.bpn.trainme.presentation.features.home.HomeDestination
import com.bpn.trainme.presentation.features.login.LoginDestination
import com.bpn.trainme.presentation.features.register.RegisterDestination

class NavigationManager {
    private var navController : NavController ?= null

    fun init(navController: NavController){
        if(this.navController == null){
            this.navController = navController
        }else{
            throw IllegalStateException("NavigationManager has already been initialized")
        }
    }

    fun navigateToLogin(){
        navController?.navigate(LoginDestination)?: throw IllegalStateException("NavigationManager has not been initialized")
    }

    fun navigateToRegister(){
        navController?.navigate(RegisterDestination)?: throw IllegalStateException("NavigationManager has not been initialized")
    }

    fun navigateToHome(){
        navController?.navigate(HomeDestination)?: throw IllegalStateException("NavigationManager has not been initialized")
    }

    fun goBack(){
        navController?.popBackStack()?: throw IllegalStateException("NavigationManager has not been initialized")
    }
}
