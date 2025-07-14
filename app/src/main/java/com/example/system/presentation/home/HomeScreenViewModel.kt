package com.example.system.presentation.home

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.system.domain.usecases.stats.StatsUseCases
import com.example.system.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    val useCases: StatsUseCases
): ViewModel() {
    private var _state = MutableStateFlow(HomeState())
    val state = _state

    init {
        viewModelScope.launch {
            useCases.getAgility().collect { agility ->
                _state.value = _state.value.copy(agility = agility)
            }
        }
        viewModelScope.launch {
            useCases.getStrength().collect { strength ->
                _state.value = _state.value.copy(strength = strength)
            }
        }
        viewModelScope.launch {
            useCases.getVitality().collect { vitality ->
                _state.value = _state.value.copy(vitality = vitality)
            }
        }
        viewModelScope.launch {
            useCases.getIntelligence().collect { intelligence ->
                _state.value = _state.value.copy(intelligence = intelligence)
            }
        }
        viewModelScope.launch {
            useCases.getLevel().collect { level ->
                _state.value = _state.value.copy(level = level)
            }
        }
        viewModelScope.launch {
            useCases.getTitle().collect { title ->
                _state.value = _state.value.copy(title = title)
            }
        }
    }

    fun onEvent(events: HomeScreenEvents, backStack: SnapshotStateList<Screen>){
        when(events) {
            HomeScreenEvents.onDailyQuestClicked -> {
                backStack.add(Screen.DailyQuests)
            }
            HomeScreenEvents.onLogsQuestClicked -> {

            }
            HomeScreenEvents.onSettingsQuestClicked -> {

            }
            HomeScreenEvents.onWeeklyQuestClicked -> {
                backStack.add(Screen.WeeklyQuests)
            }
            HomeScreenEvents.updateLevel -> {
                viewModelScope.launch {
                    useCases.setLevel(_state.value.level+1)
                }
            }
        }
    }
}