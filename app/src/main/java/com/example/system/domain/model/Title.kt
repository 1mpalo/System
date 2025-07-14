package com.example.system.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Titles")
data class Title(
    @PrimaryKey
    val title: String
)