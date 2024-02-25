package com.hegunhee.maplemfinder.core.data.dataSource.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class HistoryPreferencesDataStore @Inject constructor(
    @Named("history") private val dataStore: DataStore<Preferences>
){

    object PreferenceKey {
        val HISTORY_OCID = stringSetPreferencesKey("HISTORY_OCID")
    }

    val historyOcid = dataStore.data.map { preferences ->
        preferences[PreferenceKey.HISTORY_OCID] ?: emptySet()
    }

    suspend fun updateHistoryOcid(historyOcid : Set<String>) {
        dataStore.edit { preferences ->
            preferences[PreferenceKey.HISTORY_OCID] = historyOcid
        }
    }
}