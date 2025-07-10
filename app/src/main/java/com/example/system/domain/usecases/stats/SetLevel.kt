package com.example.system.domain.usecases.stats

import com.example.system.domain.manager.LocalUserManager
import com.example.system.unil.Constants.LEVEL

class SetLevel (
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke(value: Int) {
        localUserManager.setInt(key = LEVEL, value = value)
    }

}