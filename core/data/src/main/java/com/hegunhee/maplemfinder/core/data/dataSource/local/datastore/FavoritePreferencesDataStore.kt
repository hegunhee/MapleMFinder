package com.hegunhee.maplemfinder.core.data.dataSource.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class FavoritePreferencesDataStore @Inject constructor(
    @Named("favorite") private val dataStore : DataStore<Preferences>
){

    object PreferenceKey {
        val FAVORITE_OCID = stringSetPreferencesKey("FAVORITE_OCID")
    }

    val favoriteOcid = dataStore.data.map { preferences ->
        preferences[PreferenceKey.FAVORITE_OCID] ?: emptySet()
    }

    suspend fun updateFavoriteOcid(favoriteOcid : Set<String>) {
        dataStore.edit { preferences ->
            preferences[PreferenceKey.FAVORITE_OCID] = favoriteOcid
        }
    }
}