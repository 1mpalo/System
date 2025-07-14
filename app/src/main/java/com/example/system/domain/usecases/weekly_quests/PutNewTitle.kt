package com.example.system.domain.usecases.weekly_quests

import com.example.system.domain.model.Title
import com.example.system.domain.repository.TitleRepository

class PutNewTitle (
    private val titleRepository: TitleRepository
){

    suspend operator fun invoke(title: Title) {
        titleRepository.putNewTitle(title = title)
    }

}