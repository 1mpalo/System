package com.example.system.domain.usecases.weekly_quests

import com.example.system.domain.model.WeeklyQuest
import com.example.system.domain.repository.WeeklyQuestRepository
import kotlinx.coroutines.flow.Flow

class GetAllQuestsWeekly(
    private val weeklyQuestRepository: WeeklyQuestRepository
) {

    operator fun invoke() : Flow<List<WeeklyQuest>> {
        return weeklyQuestRepository.getAllQuests()
    }

}