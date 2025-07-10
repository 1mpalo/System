package com.example.system.presentation.weekly_quests

import com.example.system.domain.model.WeeklyQuest

sealed class WeeklyQuestsEvents {
    data class OnPlusClicked(val quest: WeeklyQuest) : WeeklyQuestsEvents()
    data class OnMinusClicked(val quest: WeeklyQuest) : WeeklyQuestsEvents()
}