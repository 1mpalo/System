package com.example.system.domain.usecases.weekly_quests

import com.example.system.domain.repository.WeeklyQuestRepository

class DeleteAllQuestsWeekly(
    private val weeklyQuestRepository: WeeklyQuestRepository
) {

    suspend operator fun invoke() {
        weeklyQuestRepository.deleteAllQuests()
    }

}