package com.example.system.presentation.home

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.system.domain.model.Title
import com.example.system.domain.usecases.stats.StatsUseCases
import com.example.system.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    val statsUseCases: StatsUseCases
): ViewModel() {
    private var _state = MutableStateFlow(HomeState())
    val state = _state

    private val _titles = MutableStateFlow<List<Title>>(value = listOf())
    val titles = _titles

    init {
        viewModelScope.launch {
            statsUseCases.getAgility().collect { agility ->
                _state.value = _state.value.copy(agility = agility)
            }
        }
        viewModelScope.launch {
            statsUseCases.getStrength().collect { strength ->
                _state.value = _state.value.copy(strength = strength)
            }
        }
        viewModelScope.launch {
            statsUseCases.getVitality().collect { vitality ->
                _state.value = _state.value.copy(vitality = vitality)
            }
        }
        viewModelScope.launch {
            statsUseCases.getIntelligence().collect { intelligence ->
                _state.value = _state.value.copy(intelligence = intelligence)
            }
        }
        viewModelScope.launch {
            statsUseCases.getLevel().collect { level ->
                _state.value = _state.value.copy(level = level)
            }
        }
        viewModelScope.launch {
            statsUseCases.getTitle().collect { title ->
                _state.value = _state.value.copy(title = title)
            }
        }
        viewModelScope.launch {
            statsUseCases.getAllTitles().collect { titles ->
                _titles.value = titles
            }
        }
    }

    fun onEvent(events: HomeScreenEvents, backStack: SnapshotStateList<Screen>){
        when(events) {
            HomeScreenEvents.OnDailyQuestClicked -> {
                backStack.add(Screen.DailyQuests)
            }
            HomeScreenEvents.OnLogsQuestClicked -> {

            }
            HomeScreenEvents.OnSettingsQuestClicked -> {

            }
            HomeScreenEvents.OnWeeklyQuestClicked -> {
                backStack.add(Screen.WeeklyQuests)
            }
            HomeScreenEvents.UpdateLevel -> {
                viewModelScope.launch {
                    statsUseCases.setLevel(_state.value.level+1)
                }
            }
            is HomeScreenEvents.OnTitleClicked -> {
                viewModelScope.launch {
                    statsUseCases.setTitle(value = events.title.title)
                }
            }
        }
    }
}