package com.example.system.unil

import com.example.system.domain.model.DailyQuest
import com.example.system.domain.model.WeeklyQuest

object Constants {
    const val USER_SETTINGS = "user-setting"
    const val APP_ENTRY = "app-entry"
    const val AGILITY = "agility"
    const val STRENGTH = "strength"
    const val INTELLIGENCE = "intelligence"
    const val VITALITY = "vitality"
    const val LEVEL = "level"
    const val DAILY_QUEST_DATABASE = "daily-quest-database"
    const val LAST_UPDATED_DAILY_QUESTS_TIME = "last-update-time_daily"
    const val LAST_UPDATED_WEEKLY_QUESTS_TIME = "last-update-time_weekly"

    val dailyQuests: List<DailyQuest> = listOf (
        DailyQuest(quest = "Learn physics.", points = "2 points of intelligence"),
        DailyQuest(quest ="Learn English.", points = "1 point of intelligence"),
        DailyQuest(quest ="Learn programming." , points = "3 points of intelligence"),
        DailyQuest(quest ="Do 100 push-ups.", points = "1 point of strength"),
        DailyQuest(quest ="Do 100 squats", points = "1 point of strength"),
        DailyQuest(quest ="Do a full training.", points = "3 points of strength"),
        DailyQuest(quest ="Marathon-kind running.", points = "3 points of agility"),
        DailyQuest(quest ="Jogging.", points = "1 point of agility"),
        DailyQuest(quest ="Hang out with friends.", points = "2 points of vitality"),
        DailyQuest(quest ="Play the guitar (at least 30 min).", points = "1 point of vitality")
    )

    val weeklyQuests: List<WeeklyQuest> = listOf (
        WeeklyQuest(quest = "Solve 5 problems from Kirik.", currentTimesDone = 0, targetTimesDone = 5),
        WeeklyQuest(quest = "Do 4 trainings.", currentTimesDone = 0, targetTimesDone = 4),
        WeeklyQuest(quest = "Watch an English movie.", currentTimesDone = 0, targetTimesDone = 1),
        WeeklyQuest(quest = "Write an English essay.", currentTimesDone = 0, targetTimesDone = 1),
        WeeklyQuest(quest = "Implement 3 features.", currentTimesDone = 0, targetTimesDone = 3),
        WeeklyQuest(quest = "Practice English speaking.", currentTimesDone = 0, targetTimesDone = 1),
        WeeklyQuest(quest = "Ride 50 km on a bike.", currentTimesDone = 0, targetTimesDone = 1),
        WeeklyQuest(quest = "Organize your study/work desk.", currentTimesDone = 0, targetTimesDone = 1),
        WeeklyQuest(quest = "Read 100 pages of a book.", currentTimesDone = 0, targetTimesDone = 1),
        WeeklyQuest(quest = "Cook a healthy meal.", currentTimesDone = 0, targetTimesDone = 2),
        WeeklyQuest(quest = "Learn 10 new English words.", currentTimesDone = 0, targetTimesDone = 3),
        WeeklyQuest(quest = "Solve 5 Leetcode problems.", currentTimesDone = 0, targetTimesDone = 5),
        WeeklyQuest(quest = "Avoid sugar for a day.", currentTimesDone = 0, targetTimesDone = 2),
        WeeklyQuest(quest = "Take a cold shower and don’t complain.", currentTimesDone = 0, targetTimesDone = 2),
        WeeklyQuest(quest = "Do absolutely nothing for 10 minutes — just exist.(rare)", currentTimesDone = 0, targetTimesDone = 3),
        WeeklyQuest(quest = "Imagine Earth was colonized by aliens — write the first report home.(rare)", currentTimesDone = 0, targetTimesDone = 1),
        WeeklyQuest(quest = "Write instructions for surviving in a world without electricity.(rare)", currentTimesDone = 0, targetTimesDone = 1),
    )
}