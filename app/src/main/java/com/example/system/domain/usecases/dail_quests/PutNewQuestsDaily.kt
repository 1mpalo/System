package com.example.system.domain.usecases.dail_quests

import com.example.system.domain.model.DailyQuest
import com.example.system.domain.repository.DailyQuestRepository

class PutNewQuestsDaily(
    private val dailyQuestRepository: DailyQuestRepository
) {

    suspend operator fun invoke(quests: List<DailyQuest>) {
        dailyQuestRepository.putNewQuests(quests)
    }

}