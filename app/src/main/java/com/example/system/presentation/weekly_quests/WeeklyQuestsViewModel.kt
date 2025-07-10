package com.example.system.presentation.weekly_quests

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.system.domain.model.DailyQuest
import com.example.system.domain.model.WeeklyQuest
import com.example.system.domain.usecases.weekly_quests.WeeklyQuestUseCases
import com.example.system.unil.Constants.weeklyQuests
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDate

@HiltViewModel
class WeeklyQuestsViewModel @Inject constructor(
    private val weeklyQuestUseCases: WeeklyQuestUseCases
) : ViewModel() {

    private var _quests = MutableStateFlow<List<WeeklyQuest>>(emptyList())
    val quests = _quests

    init {
        viewModelScope.launch {
            updateQuests()
            weeklyQuestUseCases.setLastUpdatedTimeWeekly(100)
            weeklyQuestUseCases.getAllQuests().collect { quests ->
                _quests.value = quests
            }
        }
    }

    fun onEvent(event: WeeklyQuestsEvents) {
        when(event) {
            is WeeklyQuestsEvents.OnPlusClicked -> {
                if(event.quest.currentTimesDone != event.quest.targetTimesDone) {
                    val newCurrent = event.quest.currentTimesDone + 1
                    viewModelScope.launch {
                        weeklyQuestUseCases.updateCurrentTimesDone(quest = event.quest.quest, newCurrent = newCurrent)
                    }
                } else {
                    viewModelScope.launch {
                        weeklyQuestUseCases.deleteQuest(event.quest)
                    }
                }
            }
            is WeeklyQuestsEvents.OnMinusClicked -> {
                if(event.quest.currentTimesDone > 0) {
                    viewModelScope.launch {
                        val newCurrent = event.quest.currentTimesDone - 1
                        weeklyQuestUseCases.updateCurrentTimesDone(quest = event.quest.quest, newCurrent = newCurrent)
                    }
                }
            }
        }
    }

    private suspend fun updateQuests() {
        if(shouldUpdateQuests()) {
            weeklyQuestUseCases.deleteAllQuests()
            val newQuests: MutableList<WeeklyQuest> = mutableListOf()
            for(i in 1..3) {
                val quest = (0..weeklyQuests.size).random()
                newQuests.add(weeklyQuests[quest])
            }
            weeklyQuestUseCases.putNewQuests(newQuests)
            weeklyQuestUseCases.setLastUpdatedTimeWeekly(value = LocalDate.now().dayOfYear)
        }
    }

    private suspend fun shouldUpdateQuests() : Boolean {
        val currentDate = LocalDate.now().dayOfYear
        val lastUpdatedDate = weeklyQuestUseCases.getLastUpdatedTimeWeekly().first()
        return currentDate != lastUpdatedDate
    }

}