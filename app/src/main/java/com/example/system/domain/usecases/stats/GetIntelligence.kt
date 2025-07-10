package com.example.system.domain.usecases.stats

import com.example.system.domain.manager.LocalUserManager
import com.example.system.unil.Constants.AGILITY
import com.example.system.unil.Constants.INTELLIGENCE
import kotlinx.coroutines.flow.Flow

class GetIntelligence(
    private val localUserManager: LocalUserManager
) {

    operator fun invoke() : Flow<Int> {
        return localUserManager.getInt(key = INTELLIGENCE)
    }

}