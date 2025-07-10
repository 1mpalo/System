package com.example.system.data.repository

import com.example.system.data.local.WeeklyQuestDao
import com.example.system.domain.model.WeeklyQuest
import com.example.system.domain.repository.WeeklyQuestRepository
import kotlinx.coroutines.flow.Flow

class WeeklyQuestRepositoryImplementation(
    private val dao: WeeklyQuestDao
) : WeeklyQuestRepository {

    override suspend fun putNewQuest(quest: WeeklyQuest) {
        dao.insertNewQuest(quest)
    }

    override suspend fun putNewQuests(quests: List<WeeklyQuest>) {
        dao.insertNewQuests(quests)
    }

    override fun getAllQuests(): Flow<List<WeeklyQuest>> {
        return dao.getAllQuests()
    }

    override suspend fun updateCurrentTimesDone(quest: String, newCurrent: Int) {
        dao.updateCurrentTimesDone(quest = quest, newCurrent = newCurrent)
    }

    override suspend fun deleteQuest(quest: WeeklyQuest) {
        dao.deleteQuest(quest)
    }

    override suspend fun deleteAllQuests() {
        dao.deleteAllQuests()
    }

}