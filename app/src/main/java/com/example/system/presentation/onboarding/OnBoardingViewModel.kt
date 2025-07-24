package com.example.system.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.system.domain.manager.LocalUserManager
import com.example.system.domain.usecases.app_entry.AppEntryUseCases
import com.example.system.domain.usecases.dail_quests.DailyQuestUseCases
import com.example.system.domain.usecases.weekly_quests.WeeklyQuestUseCases
import com.example.system.unil.Constants.dailyQuests
import com.example.system.unil.Constants.weeklyQuests
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import java.time.LocalDate
import kotlin.system.exitProcess


@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases,
    private val dailyQuestUseCases: DailyQuestUseCases,
    private val weeklyQuestUseCases: WeeklyQuestUseCases
): ViewModel() {
    fun saveEntry() {
        viewModelScope.launch{
            appEntryUseCases.saveFirstEntry()

        }

        viewModelScope.launch {
            dailyQuestUseCases.deleteAllQuests()
            dailyQuestUseCases.putNewQuests(quests = dailyQuests)
            dailyQuestUseCases.setLastUpdatedTime(value = LocalDate.now().dayOfYear)
        }

        viewModelScope.launch {
            weeklyQuestUseCases.deleteAllQuests()
            val newQuests = weeklyQuests.shuffled().take(3)
            weeklyQuestUseCases.putNewQuests(newQuests)
            weeklyQuestUseCases.setLastUpdatedTimeWeekly(value = LocalDate.now().dayOfYear)
        }
    }
}