package com.example.system.domain.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.system.domain.model.DailyQuest
import com.example.system.domain.model.WeeklyQuest
import kotlinx.coroutines.flow.Flow

interface WeeklyQuestRepository {

    suspend fun putNewQuest(quest: WeeklyQuest)

    suspend fun putNewQuests(quests: List<WeeklyQuest>)

    fun getAllQuests() : Flow<List<WeeklyQuest>>

    suspend fun updateCurrentTimesDone(quest: String, newCurrent: Int)

    suspend fun deleteQuest(quest: WeeklyQuest)

    suspend fun deleteAllQuests()

}