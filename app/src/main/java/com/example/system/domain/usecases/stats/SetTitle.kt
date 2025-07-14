package com.example.system.domain.usecases.stats

import com.example.system.domain.manager.LocalUserManager
import com.example.system.unil.Constants.TITLE

class SetTitle (
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke(value: String) {
        localUserManager.setString(key = TITLE, value = value)
    }

}