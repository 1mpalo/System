package com.example.system.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.system.domain.manager.LocalUserManager
import com.example.system.domain.usecases.app_entry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import kotlin.system.exitProcess


@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    val appEntryUseCases: AppEntryUseCases
): ViewModel() {
    fun saveEntry() {
        viewModelScope.launch{
            appEntryUseCases.saveFirstEntry()
        }
    }
}