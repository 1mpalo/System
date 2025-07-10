package com.example.system.domain.usecases.app_entry

import com.example.system.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {

    operator fun invoke() : Flow<Boolean> {
        return localUserManager.readyAppEntry()
    }

}