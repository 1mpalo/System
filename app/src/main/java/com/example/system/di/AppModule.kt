package com.example.system.di

import android.app.Application
import androidx.room.Room
import com.example.system.data.local.DailyQuestDao
import com.example.system.data.local.QuestsDataBase
import com.example.system.data.local.TitleDao
import com.example.system.data.local.WeeklyQuestDao
import com.example.system.data.manager.LocalUserManagerImplementation
import com.example.system.data.repository.DailyQuestRepositoryImplementation
import com.example.system.data.repository.TitleRepositoryImplementation
import com.example.system.data.repository.WeeklyQuestRepositoryImplementation
import com.example.system.domain.manager.LocalUserManager
import com.example.system.domain.repository.DailyQuestRepository
import com.example.system.domain.repository.TitleRepository
import com.example.system.domain.repository.WeeklyQuestRepository
import com.example.system.domain.usecases.app_entry.AppEntryUseCases
import com.example.system.domain.usecases.app_entry.ReadAppEntry
import com.example.system.domain.usecases.app_entry.SaveFirstEntry
import com.example.system.domain.usecases.dail_quests.DailyQuestUseCases
import com.example.system.domain.usecases.dail_quests.DeleteAllQuestsDaily
import com.example.system.domain.usecases.dail_quests.DeleteQuestDaily
import com.example.system.domain.usecases.dail_quests.GetAllQuestsDaily
import com.example.system.domain.usecases.dail_quests.GetLastUpdatedTimeDaily
import com.example.system.domain.usecases.dail_quests.PutNewQuestsDaily
import com.example.system.domain.usecases.dail_quests.SetLastUpdatedTimeDaily
import com.example.system.domain.usecases.stats.GetAgility
import com.example.system.domain.usecases.stats.GetAllTitles
import com.example.system.domain.usecases.stats.GetIntelligence
import com.example.system.domain.usecases.stats.GetLevel
import com.example.system.domain.usecases.stats.GetStrength
import com.example.system.domain.usecases.stats.GetTitle
import com.example.system.domain.usecases.stats.GetVitality
import com.example.system.domain.usecases.stats.SetAgility
import com.example.system.domain.usecases.stats.SetIntelligence
import com.example.system.domain.usecases.stats.SetLevel
import com.example.system.domain.usecases.stats.SetStrength
import com.example.system.domain.usecases.stats.SetTitle
import com.example.system.domain.usecases.stats.SetVitality
import com.example.system.domain.usecases.stats.StatsUseCases
import com.example.system.domain.usecases.weekly_quests.CheckATitleForExistence
import com.example.system.domain.usecases.weekly_quests.DeleteAllQuestsWeekly
import com.example.system.domain.usecases.weekly_quests.DeleteQuestWeekly
import com.example.system.domain.usecases.weekly_quests.GetAllQuestsWeekly
import com.example.system.domain.usecases.weekly_quests.GetLastUpdatedTimeWeekly
import com.example.system.domain.usecases.weekly_quests.PutNewQuestWeekly
import com.example.system.domain.usecases.weekly_quests.PutNewQuestsWeekly
import com.example.system.domain.usecases.weekly_quests.PutNewTitle
import com.example.system.domain.usecases.weekly_quests.SetLastUpdatedTimeWeekly
import com.example.system.domain.usecases.weekly_quests.UpdateCurrentTimesDone
import com.example.system.domain.usecases.weekly_quests.WeeklyQuestUseCases
import com.example.system.unil.Constants.DAILY_QUEST_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ) : LocalUserManager = LocalUserManagerImplementation(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveFirstEntry = SaveFirstEntry(localUserManager)
    )
    
    @Provides
    @Singleton
    fun provideStatsUseCases(
        localUserManager: LocalUserManager,
        titleRepository: TitleRepository
    ) : StatsUseCases = StatsUseCases(
        setAgility = SetAgility(localUserManager),
        getAgility = GetAgility(localUserManager),
        setIntelligence = SetIntelligence(localUserManager),
        getIntelligence = GetIntelligence(localUserManager),
        setStrength = SetStrength(localUserManager),
        getStrength = GetStrength(localUserManager),
        setVitality = SetVitality(localUserManager),
        getVitality = GetVitality(localUserManager),
        setLevel = SetLevel(localUserManager),
        getLevel = GetLevel(localUserManager),
        setTitle = SetTitle(localUserManager),
        getTitle = GetTitle(localUserManager),
        getAllTitles = GetAllTitles(titleRepository)
    )

    @Provides
    @Singleton
    fun provideQuestsDataBase(
        application: Application
    ) : QuestsDataBase {
        return Room.databaseBuilder(
            context = application,
            klass = QuestsDataBase::class.java,
            name = DAILY_QUEST_DATABASE
        )
        .fallbackToDestructiveMigration()
        .build()
    }

    @Provides
    @Singleton
    fun provideDailyQuestDao(
        questDataBase : QuestsDataBase
    ) : DailyQuestDao = questDataBase.dailyQuestDao

    @Provides
    @Singleton
    fun provideWeeklyQuestDao(
        questsDataBase: QuestsDataBase
    ) : WeeklyQuestDao = questsDataBase.weeklyQuestDao

    @Provides
    @Singleton
    fun provideTitleDao(
        questDataBase: QuestsDataBase
    ) : TitleDao = questDataBase.titleDao

    @Provides
    @Singleton
    fun provideDailyQuestRepositoryImplementation(
        dailyQuestDao: DailyQuestDao
    ) : DailyQuestRepository = DailyQuestRepositoryImplementation(dao = dailyQuestDao)

    @Provides
    @Singleton
    fun provideWeeklyQuestRepositoryImplementation(
        weeklyQuestDao: WeeklyQuestDao
    ) : WeeklyQuestRepository = WeeklyQuestRepositoryImplementation(dao = weeklyQuestDao)

    @Provides
    @Singleton
    fun provideTitleRepositoryImplementation(
        titleDao: TitleDao
    ) : TitleRepository = TitleRepositoryImplementation(dao = titleDao)

    @Provides
    @Singleton
    fun provideDailyQuestUseCases(
        dailyQuestRepository: DailyQuestRepository,
        localUserManager: LocalUserManager
    ) : DailyQuestUseCases = DailyQuestUseCases(
        putNewQuests = PutNewQuestsDaily(dailyQuestRepository),
        getAllQuests = GetAllQuestsDaily(dailyQuestRepository),
        deleteQuest = DeleteQuestDaily(dailyQuestRepository),
        deleteAllQuests = DeleteAllQuestsDaily(dailyQuestRepository),
        setLastUpdatedTime = SetLastUpdatedTimeDaily(localUserManager),
        getLastUpdatedTime = GetLastUpdatedTimeDaily(localUserManager)
    )

    @Provides
    @Singleton
    fun provideWeeklyQuestUseCases(
        weeklyQuestRepository: WeeklyQuestRepository,
        localUserManager: LocalUserManager,
        titleRepository: TitleRepository
    ) : WeeklyQuestUseCases = WeeklyQuestUseCases(
        putNewQuest = PutNewQuestWeekly(weeklyQuestRepository),
        putNewQuests = PutNewQuestsWeekly(weeklyQuestRepository),
        getAllQuests = GetAllQuestsWeekly(weeklyQuestRepository),
        updateCurrentTimesDone = UpdateCurrentTimesDone(weeklyQuestRepository),
        deleteQuest = DeleteQuestWeekly(weeklyQuestRepository),
        deleteAllQuests = DeleteAllQuestsWeekly(weeklyQuestRepository),
        setLastUpdatedTimeWeekly = SetLastUpdatedTimeWeekly(localUserManager),
        getLastUpdatedTimeWeekly = GetLastUpdatedTimeWeekly(localUserManager),
        putNewTitle = PutNewTitle(titleRepository),
        checkATitleForExistence = CheckATitleForExistence(titleRepository)
    )
}