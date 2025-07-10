package com.example.system

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.system.domain.usecases.app_entry.AppEntryUseCases
import com.example.system.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    var startDestination by mutableStateOf<Screen?>(null)
        private set

    var isLoading by mutableStateOf<Boolean>(true)
        private set

    init {
        appEntryUseCases.readAppEntry()
            .onStart{
                Log.d("isLoading","$isLoading")
                isLoading = true
            }
            .onEach() { shouldStartFromHomeScreen ->
                if(shouldStartFromHomeScreen) {
                    startDestination = Screen.HomeScreen
                } else {
                    startDestination = Screen.OnBoardingScreen
                }
                isLoading = false
            }.launchIn(viewModelScope)
    }

}