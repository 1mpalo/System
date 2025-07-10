package com.example.system.domain.usecases.stats

import com.example.system.domain.manager.LocalUserManager
import com.example.system.unil.Constants.INTELLIGENCE
import com.example.system.unil.Constants.VITALITY
import kotlinx.coroutines.flow.Flow

class GetVitality(
    private val localUserManager: LocalUserManager
) {

    operator fun invoke() : Flow<Int> {
        return localUserManager.getInt(key = VITALITY)
    }

}