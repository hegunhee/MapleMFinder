package com.hegunhee.maplemfinder.core.data.dataSource.local

import com.hegunhee.maplefinder.core.model.MapleMWorld
import com.hegunhee.maplemfinder.core.data.dataSource.local.datastore.FavoritePreferencesDataStore
import com.hegunhee.maplemfinder.core.data.dataSource.local.datastore.HistoryPreferencesDataStore
import com.hegunhee.maplemfinder.core.data.mapper.worldList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultLocalSource @Inject constructor(
    private val preferenceManager : PreferenceManager,
    private val favoriteDataStore : FavoritePreferencesDataStore,
    private val historyDataStore : HistoryPreferencesDataStore
) : LocalDataSource{

    override fun getMainOcid(): String {
        return preferenceManager.getMainOcid()
    }

    override fun setMainOcid(ocid: String) {
        preferenceManager.setMainOcid(ocid)
    }

    override fun isMainOcid(ocid: String): Boolean {
        return preferenceManager.isMainOcid(ocid)
    }

    override fun getWorldList(): List<MapleMWorld> {
        return worldList
    }

    override fun getFavoriteOcids(): Flow<Set<String>> {
        return favoriteDataStore.favoriteOcid
    }

    override suspend fun toggleFavoriteOcid(favoriteOcids : Set<String>) {
        favoriteDataStore.updateFavoriteOcid(favoriteOcids)
    }

    override fun getHistoryOcids(): Flow<Set<String>> {
        return historyDataStore.historyOcid
    }

    override suspend fun toggleHistoryOcid(historyOcids: Set<String>) {
        historyDataStore.updateHistoryOcid(historyOcids)
    }
}