package com.example.system.domain.usecases.weekly_quests

import com.example.system.domain.manager.LocalUserManager
import com.example.system.unil.Constants.LAST_UPDATED_WEEKLY_QUESTS_TIME
import kotlinx.coroutines.flow.Flow

class GetLastUpdatedTimeWeekly(
    private val localUserManager: LocalUserManager
) {

    operator fun invoke() : Flow<Int> {
        return localUserManager.getInt(key = LAST_UPDATED_WEEKLY_QUESTS_TIME)
    }

}