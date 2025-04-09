package com.bpn.trainme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.bpn.trainme.presentation.navigation.AppNavigation
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
            TrainMeTheme {
                Surface{
                    AppNavigation(navigationManager = navigationManager)
                }
            }
        }
    }
}
