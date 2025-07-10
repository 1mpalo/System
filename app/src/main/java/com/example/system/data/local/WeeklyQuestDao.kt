package com.example.system.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.system.domain.model.WeeklyQuest
import kotlinx.coroutines.flow.Flow


@Dao
interface WeeklyQuestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewQuest(quest: WeeklyQuest)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewQuests(quests: List<WeeklyQuest>)

    @Query("SELECT * FROM WeeklyQuests")
    fun getAllQuests() : Flow<List<WeeklyQuest>>

    @Query("UPDATE WeeklyQuests SET currentTimesDone = :newCurrent WHERE quest = :quest")
    suspend fun updateCurrentTimesDone(quest: String, newCurrent: Int)

    @Delete
    suspend fun deleteQuest(quest: WeeklyQuest)

    @Query("DELETE FROM WeeklyQuests")
    suspend fun deleteAllQuests()

}