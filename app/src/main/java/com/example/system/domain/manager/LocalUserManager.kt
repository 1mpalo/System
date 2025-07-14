package com.example.system.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {

    suspend fun saveAppEntry()

    fun readyAppEntry() : Flow<Boolean>

    suspend fun setInt(key: String, value: Int)

    fun getInt(key: String) : Flow<Int>

    suspend fun setString(key: String, value: String)

    fun getString(key: String) : Flow<String>

}