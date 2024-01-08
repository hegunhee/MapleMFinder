package com.hegunhee.maplemfinder.core.data.dataSource.local

import com.hegunhee.maplefinder.core.model.mapleM.MapleMWorld
import com.hegunhee.maplemfinder.core.data.R
import com.hegunhee.maplemfinder.core.data.mapper.worldList
import javax.inject.Inject

class DefaultMapleMLocalSource @Inject constructor(
    private val mapleMPreferenceManager : MapleMPreferenceManager
) : MapleMLocalDataSource{

    override fun getMainOcid(): String {
        return mapleMPreferenceManager.getMainOcid()
    }

    override fun setMainOcid(ocid: String) {
        mapleMPreferenceManager.setMainOcid(ocid)
    }

    override fun isMainOcid(ocid: String): Boolean {
        return mapleMPreferenceManager.isMainOcid(ocid)
    }

    override fun isFavoriteListEmpty(): Boolean {
        return mapleMPreferenceManager.isFavoriteListEmpty()
    }

    override fun getWorldList(): List<MapleMWorld> {
        return worldList
    }

    override fun isFavoriteOcid(ocid: String): Boolean {
        return mapleMPreferenceManager.isFavoriteOcid(ocid)
    }

    override fun toggleFavoriteOcid(ocid: String) {
        mapleMPreferenceManager.toggleFavoriteOcid(ocid)
    }

    override fun getWorldIcon(name: String): Int {
        return worldList.find { it.name == name }?.icon ?: R.drawable.ic_default_server_mark_24
    }
}