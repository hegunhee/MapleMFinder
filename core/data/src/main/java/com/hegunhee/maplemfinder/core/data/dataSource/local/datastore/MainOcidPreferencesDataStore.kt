package com.hegunhee.maplemfinder.core.data.dataSource.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class MainOcidPreferencesDataStore @Inject constructor(
    @Named("mainOcid") private val dataStore: DataStore<Preferences>
) {

    object PreferenceKey {
        val MAIN_OCID = stringPreferencesKey("MAIN_OCID")
    }

    val mainOcid = dataStore.data.map { preferences ->
        preferences[PreferenceKey.MAIN_OCID] ?: ""
    }

    suspend fun updateMainOcid(mainOcid : String) {
        dataStore.edit { preferences ->
            preferences[PreferenceKey.MAIN_OCID] = mainOcid
        }
    }
}