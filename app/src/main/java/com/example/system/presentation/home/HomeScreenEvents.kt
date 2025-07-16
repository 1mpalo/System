package com.example.system.presentation.home
import com.example.system.domain.model.Title

sealed class HomeScreenEvents {
    object OnDailyQuestClicked : HomeScreenEvents()
    object OnWeeklyQuestClicked : HomeScreenEvents()
    object OnLogsQuestClicked : HomeScreenEvents()
    object OnSettingsQuestClicked : HomeScreenEvents()
    object UpdateLevel : HomeScreenEvents()
    data class OnTitleClicked (val title: Title) : HomeScreenEvents()
}