package com.example.system.domain.usecases.stats

import com.example.system.domain.manager.LocalUserManager
import com.example.system.unil.Constants.STRENGTH
import kotlinx.coroutines.flow.Flow

class GetStrength(
    private val localUserManager: LocalUserManager
) {

    operator fun invoke() : Flow<Int> {
        return localUserManager.getInt(key = STRENGTH)
    }

}