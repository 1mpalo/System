package com.example.system.domain.usecases.dail_quests

import com.example.system.domain.model.DailyQuest
import com.example.system.domain.repository.DailyQuestRepository
import kotlinx.coroutines.flow.Flow

class GetAllQuestsDaily(
    private val dailyQuestRepository: DailyQuestRepository
) {

    operator fun invoke() : Flow<List<DailyQuest>> {
        return dailyQuestRepository.getAllQuests()
    }

}