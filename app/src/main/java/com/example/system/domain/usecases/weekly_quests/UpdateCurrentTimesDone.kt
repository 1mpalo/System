package com.example.system.domain.usecases.weekly_quests

import com.example.system.domain.repository.WeeklyQuestRepository

class UpdateCurrentTimesDone(
    private val weeklyQuestRepository: WeeklyQuestRepository
) {

    suspend operator fun invoke(quest: String, newCurrent: Int) {
        weeklyQuestRepository.updateCurrentTimesDone(quest = quest, newCurrent = newCurrent)
    }

}