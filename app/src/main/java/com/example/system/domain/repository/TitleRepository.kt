package com.example.system.domain.repository

import com.example.system.domain.model.Title
import kotlinx.coroutines.flow.Flow

interface TitleRepository {

    suspend fun putNewTitle(title: Title)

    fun getAllTitles() : Flow<List<Title>>

    suspend fun alreadyExists(title: String) : Boolean

}