package com.example.system.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.system.domain.model.DailyQuest
import com.example.system.domain.model.WeeklyQuest

@Database(
    entities = [DailyQuest::class, WeeklyQuest::class],
    version = 4
)
abstract class QuestsDataBase: RoomDatabase() {
    abstract val dailyQuestDao: DailyQuestDao
    abstract val weeklyQuestDao: WeeklyQuestDao
}