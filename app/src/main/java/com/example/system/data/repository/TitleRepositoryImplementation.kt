package com.example.system.data.repository

import com.example.system.data.local.TitleDao
import com.example.system.domain.model.Title
import com.example.system.domain.repository.TitleRepository
import kotlinx.coroutines.flow.Flow

class TitleRepositoryImplementation (
    private val dao: TitleDao
) : TitleRepository{
    override suspend fun putNewTitle(title: Title) {
        dao.insertTitle(title = title)
    }

    override fun getAllTitles(): Flow<List<Title>> {
        return dao.getAllTitles()
    }

    override suspend fun alreadyExists(title: String): Boolean {
        return dao.existsByName(titleName = title)
    }
}