package com.example.system.domain.usecases.stats

import com.example.system.domain.manager.LocalUserManager
import com.example.system.unil.Constants.INTELLIGENCE

class SetIntelligence (
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke(value: Int) {
        localUserManager.setInt(key = INTELLIGENCE, value = value)
    }

}