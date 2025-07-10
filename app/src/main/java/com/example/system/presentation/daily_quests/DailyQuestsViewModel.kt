package com.example.system.presentation.daily_quests


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.system.domain.model.DailyQuest
import com.example.system.domain.usecases.dail_quests.DailyQuestUseCases
import com.example.system.domain.usecases.stats.StatsUseCases
import com.example.system.presentation.home.HomeState
import com.example.system.unil.Constants
import com.example.system.unil.Constants.AGILITY
import com.example.system.unil.Constants.INTELLIGENCE
import com.example.system.unil.Constants.STRENGTH
import com.example.system.unil.Constants.VITALITY
import com.example.system.unil.Constants.dailyQuests
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class DailyQuestsViewModel @Inject constructor(
    private val statsUseCases: StatsUseCases,
    private val dailyQuestUseCases: DailyQuestUseCases
): ViewModel() {

    private var _quests = MutableStateFlow<List<DailyQuest>>(emptyList())
    val quests = _quests

    init {
        viewModelScope.launch {
            updateQuests()
            dailyQuestUseCases.getAllQuests().collect { quests ->
                _quests.value = quests
            }
        }
    }

    fun onDoneClicked(currentStats: HomeState, stat: String, points: Int, quest: DailyQuest) {
        viewModelScope.launch {
            when (stat) {
                AGILITY -> statsUseCases.setAgility(currentStats.agility + points)
                INTELLIGENCE -> statsUseCases.setIntelligence(currentStats.intelligence + points)
                VITALITY -> statsUseCases.setVitality(currentStats.vitality + points)
                STRENGTH -> statsUseCases.setStrength(currentStats.strength + points)
            }
        }
        viewModelScope.launch {
            dailyQuestUseCases.deleteQuest(quest = quest)
        }
    }


    private suspend fun updateQuests() {
        if(shouldUpdateQuests()) {
            dailyQuestUseCases.deleteAllQuests()
            dailyQuestUseCases.putNewQuests(quests = dailyQuests)
            dailyQuestUseCases.setLastUpdatedTime(value = LocalDate.now().dayOfYear)
        }
    }

    private suspend fun shouldUpdateQuests() : Boolean {
        val currentDate = LocalDate.now().dayOfYear
        val lastUpdatedDate = dailyQuestUseCases.getLastUpdatedTime().first()
        return currentDate != lastUpdatedDate
    }
}