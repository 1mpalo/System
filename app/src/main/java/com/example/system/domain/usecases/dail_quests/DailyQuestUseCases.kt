package com.example.system.domain.usecases.dail_quests

data class DailyQuestUseCases(
    val putNewQuests: PutNewQuestsDaily,
    val getAllQuests: GetAllQuestsDaily,
    val deleteQuest: DeleteQuestDaily,
    val deleteAllQuests: DeleteAllQuestsDaily,
    val getLastUpdatedTime: GetLastUpdatedTimeDaily,
    val setLastUpdatedTime: SetLastUpdatedTimeDaily
)