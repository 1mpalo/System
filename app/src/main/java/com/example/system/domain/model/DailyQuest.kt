package com.example.system.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DailyQuests")
data class DailyQuest(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val quest: String,
    val points: String
)