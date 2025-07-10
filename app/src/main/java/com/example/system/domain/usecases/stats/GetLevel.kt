package com.example.system.domain.usecases.stats

import com.example.system.domain.manager.LocalUserManager
import com.example.system.unil.Constants.LEVEL
import kotlinx.coroutines.flow.Flow

class GetLevel (
    private val localUserManager: LocalUserManager
) {

    operator fun invoke() : Flow<Int> {
        return localUserManager.getInt(key = LEVEL)
    }

}