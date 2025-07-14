package com.example.system.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.system.domain.model.Title
import com.example.system.domain.model.WeeklyQuest
import kotlinx.coroutines.flow.Flow

@Dao
interface TitleDao {

    @Insert
    suspend fun insertTitle(title: Title)

    @Query("SELECT * FROM Titles")
    fun getAllTitles() : Flow<List<Title>>

    @Query("SELECT EXISTS(SELECT 1 FROM Titles WHERE title = :titleName LIMIT 1)")
    suspend fun existsByName(titleName: String): Boolean

}