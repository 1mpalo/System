package com.example.system.presentation.onboarding

sealed class OnBoardingEvent {
    object SaveAppEntry: OnBoardingEvent()
    object LeaveTheApp: OnBoardingEvent()
}