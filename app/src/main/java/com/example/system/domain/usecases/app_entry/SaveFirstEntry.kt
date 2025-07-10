package com.example.system.domain.usecases.app_entry

import com.example.system.domain.manager.LocalUserManager

class SaveFirstEntry(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }


}