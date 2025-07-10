package com.example.system.presentation.navigation

import com.example.system.presentation.home.HomeState

sealed class Screen {
    data object OnBoardingScreen: Screen()
    data object WelcomingScreen: Screen()
    data object HomeScreen: Screen()
    data object DailyQuests: Screen()
    data object WeeklyQuests: Screen()
}