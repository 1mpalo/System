package com.example.system.domain.usecases.stats

import com.example.system.domain.manager.LocalUserManager
import com.example.system.unil.Constants.AGILITY

class SetAgility (
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke(value: Int) {
        localUserManager.setInt(key = AGILITY, value = value)
    }

}