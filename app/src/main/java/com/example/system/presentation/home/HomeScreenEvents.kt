package com.example.system.presentation.home

sealed class HomeScreenEvents {
    object onDailyQuestClicked : HomeScreenEvents()
    object onWeeklyQuestClicked : HomeScreenEvents()
    object onLogsQuestClicked : HomeScreenEvents()
    object onSettingsQuestClicked : HomeScreenEvents()
    object updateLevel : HomeScreenEvents()
}