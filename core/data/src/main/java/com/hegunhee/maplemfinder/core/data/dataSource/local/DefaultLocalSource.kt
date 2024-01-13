package com.hegunhee.maplemfinder.core.data.dataSource.local

import com.hegunhee.maplefinder.core.model.MapleMWorld
import com.hegunhee.maplemfinder.core.data.mapper.worldList
import javax.inject.Inject

class DefaultLocalSource @Inject constructor(
    private val preferenceManager : PreferenceManager
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

    override fun isFavoriteListEmpty(): Boolean {
        return preferenceManager.isFavoriteListEmpty()
    }

    override fun getWorldList(): List<MapleMWorld> {
        return worldList
    }

    override fun getFavoriteOcidList(): List<String> {
        return preferenceManager.getFavoriteOcidList()
    }

    override fun isFavoriteOcid(ocid: String): Boolean {
        return preferenceManager.isFavoriteOcid(ocid)
    }

    override fun toggleFavoriteOcid(ocid: String) {
        preferenceManager.toggleFavoriteOcid(ocid)
    }

    override fun getHistoryOcidList(): List<String> {
        return preferenceManager.getHistoryOcidList()
    }

    override fun addHistoryOcid(ocid: String) {
        preferenceManager.addHistoryOcid(ocid)
    }

    override fun deleteHistoryOcid(ocid: String) {
        preferenceManager.deleteHistoryOcid(ocid)
    }
}