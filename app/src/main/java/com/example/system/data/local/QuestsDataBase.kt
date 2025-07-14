package com.example.system.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.system.domain.model.DailyQuest
import com.example.system.domain.model.Title
import com.example.system.domain.model.WeeklyQuest

@Database(
    entities = [DailyQuest::class, WeeklyQuest::class, Title::class],
    version = 8
)
abstract class QuestsDataBase: RoomDatabase() {
    abstract val dailyQuestDao: DailyQuestDao
    abstract val weeklyQuestDao: WeeklyQuestDao
    abstract val titleDao: TitleDao
}