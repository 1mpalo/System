package com.example.system.domain.usecases.weekly_quests

import com.example.system.domain.manager.LocalUserManager
import com.example.system.unil.Constants.LAST_UPDATED_WEEKLY_QUESTS_TIME

class SetLastUpdatedTimeWeekly(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke(value : Int) {
        localUserManager.setInt(key = LAST_UPDATED_WEEKLY_QUESTS_TIME, value = value)
    }

}