package com.example.system.domain.repository

import com.example.system.domain.model.DailyQuest
import kotlinx.coroutines.flow.Flow

interface DailyQuestRepository {

    suspend fun putNewQuests(quests: List<DailyQuest>)

    fun getAllQuests() : Flow<List<DailyQuest>>

    suspend fun deleteQuest(quest: DailyQuest)

    suspend fun deleteAllQuests()

}