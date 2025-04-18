package com.bpn.trainme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.compose.rememberNavController
import com.bpn.trainme.presentation.navigation.AppRoot
import com.bpn.trainme.presentation.navigation.NavigationManager
import com.bpn.trainme.presentation.theme.TrainMeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            DisposableEffect(navController) {
                navigationManager.init(navController)
                onDispose { }
            }
            TrainMeTheme {
                AppRoot(navController = navController, navigationManager = navigationManager)
            }
        }
    }
}
