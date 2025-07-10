package com.example.system.domain.usecases.dail_quests

import com.example.system.domain.repository.DailyQuestRepository

class DeleteAllQuestsDaily(
    private val dailyQuestRepository: DailyQuestRepository
) {

    suspend operator fun invoke() {
        dailyQuestRepository.deleteAllQuests()
    }

}