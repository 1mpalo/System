package com.example.system.presentation.weekly_quests

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.system.domain.model.DailyQuest
import com.example.system.domain.model.Title
import com.example.system.domain.model.WeeklyQuest
import com.example.system.domain.usecases.weekly_quests.WeeklyQuestUseCases
import com.example.system.unil.Constants.CHANCE_TO_BE_REWARDED_WITH_TITLE
import com.example.system.unil.Constants.weeklyQuests
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDate
import kotlin.random.Random

@HiltViewModel
class WeeklyQuestsViewModel @Inject constructor(
    private val weeklyQuestUseCases: WeeklyQuestUseCases
) : ViewModel() {

    private val _quests = MutableStateFlow<List<WeeklyQuest>>(emptyList())
    val quests = _quests

    private val _toastFlow = MutableSharedFlow<String>()
    val toastFlow = _toastFlow

    init {
        viewModelScope.launch {
            updateQuests()
            weeklyQuestUseCases.getAllQuests().collect { quests ->
                _quests.value = quests
            }
        }
    }

    fun onEvent(event: WeeklyQuestsEvents) {
        when(event) {
            is WeeklyQuestsEvents.OnPlusClicked -> {
                val quest = event.quest
                if(quest.currentTimesDone != quest.targetTimesDone) {
                    val newCurrent = quest.currentTimesDone + 1
                    viewModelScope.launch {
                        weeklyQuestUseCases.updateCurrentTimesDone(quest = quest.quest, newCurrent = newCurrent)
                    }
                } else {
                    viewModelScope.launch {
                        weeklyQuestUseCases.deleteQuest(quest = quest)
                        if(shouldRewardWithTitle(chance = CHANCE_TO_BE_REWARDED_WITH_TITLE)) {
                            if(!weeklyQuestUseCases.checkATitleForExistence(title = quest.title)) {
                                weeklyQuestUseCases.putNewTitle(title = Title(title = quest.title))
                                _toastFlow.emit(value = "You earned new title: ${quest.title}")
                            } else {
                                _toastFlow.emit(value = "Lucky you! But you already own this one")
                            }
                        }
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
            val newQuests = weeklyQuests.shuffled().take(3)
            weeklyQuestUseCases.putNewQuests(newQuests)
            weeklyQuestUseCases.setLastUpdatedTimeWeekly(value = LocalDate.now().dayOfYear)
        }
    }

    private suspend fun shouldUpdateQuests() : Boolean {
        val currentDate = LocalDate.now().dayOfYear
        val lastUpdatedDate = weeklyQuestUseCases.getLastUpdatedTimeWeekly().first()
        return currentDate != lastUpdatedDate
    }

    private fun shouldRewardWithTitle(chance: Int) : Boolean {
        return Random.nextInt(100) < chance
    }

}