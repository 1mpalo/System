package com.example.system.data.manager

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.system.domain.manager.LocalUserManager
import com.example.system.unil.Constants
import com.example.system.unil.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences

class LocalUserManagerImplementation(
    private val context: Context
) : LocalUserManager {
    override suspend fun saveAppEntry() {
        Log.d("LocalUserManager", "Saving app entry")
        context.dataStore.edit { settings ->
            settings[PreferencesKeys.APP_ENTRY] = true
        }
    }

    override fun readyAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            Log.d("LocalUserManager", "Reading ${preferences[PreferencesKeys.APP_ENTRY]}")
            preferences[PreferencesKeys.APP_ENTRY] == true
        }
    }

    override suspend fun setInt(key: String, value: Int) {
        val preferenceKey = intPreferencesKey(key)
        Log.d("setInt", "$preferenceKey, $value")
        context.dataStore.edit {
            it[preferenceKey] = value
        }
    }

    override fun getInt(key: String) : Flow<Int> {
        val preferenceKey = intPreferencesKey(key)
        Log.d("getInt", "$preferenceKey")
        return context.dataStore.data.map { preference ->
            val value = preference[preferenceKey] ?: 0
            Log.d("getInt", "${preference[preferenceKey]}, $preferenceKey")
            value
        }
    }

    override suspend fun setString(key: String, value: String) {
        val preferenceKey = stringPreferencesKey(key)
        Log.d("setString", "$preferenceKey, $value")
        context.dataStore.edit {
            it[preferenceKey] = value
        }
    }

    override fun getString(key: String): Flow<String> {
        val preferenceKey = stringPreferencesKey(key)
        Log.d("getString", "$preferenceKey")
        return context.dataStore.data.map { preference ->
            val value = preference[preferenceKey] ?: "None"
            value
        }
    }

}

private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(name = USER_SETTINGS)

private object PreferencesKeys {
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}