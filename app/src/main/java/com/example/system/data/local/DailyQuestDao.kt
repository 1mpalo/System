package com.example.system.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.system.domain.model.DailyQuest
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyQuestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewQuests(quests : List<DailyQuest>)

    @Query("SELECT * FROM DailyQuests")
    fun getAllQuests() : Flow<List<DailyQuest>>

    @Delete
    suspend fun deleteQuest(quest: DailyQuest)

    @Query("DELETE FROM DailyQuests")
    suspend fun deleteAllQuests()

}