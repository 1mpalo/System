package com.example.system.data.repository

import com.example.system.data.local.DailyQuestDao
import com.example.system.domain.model.DailyQuest
import com.example.system.domain.repository.DailyQuestRepository
import kotlinx.coroutines.flow.Flow

class DailyQuestRepositoryImplementation(
    private val dao: DailyQuestDao
) : DailyQuestRepository {

    override suspend fun putNewQuests(quests: List<DailyQuest>) {
        dao.insertNewQuests(quests = quests)
    }

    override fun getAllQuests(): Flow<List<DailyQuest>> {
        return dao.getAllQuests()
    }

    override suspend fun deleteQuest(quest: DailyQuest) {
        dao.deleteQuest(quest = quest)
    }

    override suspend fun deleteAllQuests() {
        dao.deleteAllQuests()
    }
}