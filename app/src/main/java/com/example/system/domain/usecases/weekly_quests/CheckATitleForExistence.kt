package com.example.system.domain.usecases.weekly_quests

import com.example.system.domain.repository.TitleRepository

class CheckATitleForExistence(
    private val titleRepository: TitleRepository
) {

    suspend  operator fun invoke(title: String) : Boolean {
        return titleRepository.alreadyExists(title = title)
    }

}