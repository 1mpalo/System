package com.example.system.domain.usecases.stats

import com.example.system.domain.manager.LocalUserManager
import com.example.system.unil.Constants.TITLE
import kotlinx.coroutines.flow.Flow

class GetTitle (
    private val localUserManager: LocalUserManager
) {

    operator fun invoke() : Flow<String> {
        return localUserManager.getString(key = TITLE)
    }

}