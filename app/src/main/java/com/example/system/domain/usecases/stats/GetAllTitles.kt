package com.example.system.domain.usecases.stats

import com.example.system.domain.model.Title
import com.example.system.domain.repository.TitleRepository
import kotlinx.coroutines.flow.Flow

class GetAllTitles (
    private val titleRepository: TitleRepository
) {

    operator fun invoke() : Flow<List<Title>> {
        return titleRepository.getAllTitles()
    }

}