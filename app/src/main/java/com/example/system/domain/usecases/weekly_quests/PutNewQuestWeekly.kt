package com.example.system.domain.usecases.weekly_quests

import com.example.system.domain.model.WeeklyQuest
import com.example.system.domain.repository.WeeklyQuestRepository

class PutNewQuestWeekly(
    private val weeklyQuestRepository: WeeklyQuestRepository
) {

    suspend operator fun invoke(quest: WeeklyQuest) {
        weeklyQuestRepository.deleteQuest(quest)
    }

}