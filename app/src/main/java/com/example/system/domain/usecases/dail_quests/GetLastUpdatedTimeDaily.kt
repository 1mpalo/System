package com.example.system.domain.usecases.dail_quests

import com.example.system.domain.manager.LocalUserManager
import com.example.system.unil.Constants.LAST_UPDATED_DAILY_QUESTS_TIME
import kotlinx.coroutines.flow.Flow

class GetLastUpdatedTimeDaily(
    private val localUserManager: LocalUserManager
) {

    operator fun invoke() : Flow<Int> {
        return localUserManager.getInt(key = LAST_UPDATED_DAILY_QUESTS_TIME)
    }

}