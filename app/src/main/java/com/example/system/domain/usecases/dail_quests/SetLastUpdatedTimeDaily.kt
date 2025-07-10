package com.example.system.domain.usecases.dail_quests

import com.example.system.domain.manager.LocalUserManager
import com.example.system.unil.Constants.LAST_UPDATED_DAILY_QUESTS_TIME

class SetLastUpdatedTimeDaily(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke(value: Int) {
        localUserManager.setInt(key = LAST_UPDATED_DAILY_QUESTS_TIME, value = value)
    }

}