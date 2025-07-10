package com.example.system

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.system.presentation.navigation.NavGraph
import com.example.system.presentation.navigation.Screen
import com.example.system.presentation.onboarding.OnBoardingScreen
import com.example.system.presentation.onboarding.OnBoardingViewModel
import com.example.system.ui.theme.SystemTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val mainViewModel: MainViewModel by viewModels<MainViewModel>()
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            mainViewModel.isLoading
        }
        enableEdgeToEdge()
        setContent {
            SystemTheme {
                val startDestination = mainViewModel.startDestination
                startDestination?.let {
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
}


