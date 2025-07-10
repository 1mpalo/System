package com.example.system.domain.usecases.weekly_quests

import com.example.system.domain.model.WeeklyQuest
import com.example.system.domain.repository.WeeklyQuestRepository

class PutNewQuestsWeekly(
    private val weeklyQuestRepository: WeeklyQuestRepository
) {

    suspend operator fun invoke(quests: List<WeeklyQuest>) {
        weeklyQuestRepository.putNewQuests(quests)
    }

}