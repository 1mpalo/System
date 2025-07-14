package com.example.system.domain.usecases.weekly_quests

data class WeeklyQuestUseCases (
    val putNewQuest: PutNewQuestWeekly,
    val putNewQuests: PutNewQuestsWeekly,
    val getAllQuests: GetAllQuestsWeekly,
    val updateCurrentTimesDone: UpdateCurrentTimesDone,
    val deleteQuest: DeleteQuestWeekly,
    val deleteAllQuests: DeleteAllQuestsWeekly,
    val setLastUpdatedTimeWeekly: SetLastUpdatedTimeWeekly,
    val getLastUpdatedTimeWeekly: GetLastUpdatedTimeWeekly,
    val putNewTitle: PutNewTitle,
    val checkATitleForExistence: CheckATitleForExistence
)