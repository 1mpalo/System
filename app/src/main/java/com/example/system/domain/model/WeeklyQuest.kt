package com.example.system.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WeeklyQuests")
data class WeeklyQuest (
    @PrimaryKey
    val quest: String,
    val currentTimesDone: Int,
    val targetTimesDone: Int
)