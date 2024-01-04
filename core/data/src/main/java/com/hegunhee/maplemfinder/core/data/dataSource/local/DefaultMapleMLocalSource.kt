package com.hegunhee.maplemfinder.core.data.dataSource.local

import javax.inject.Inject

class DefaultMapleMLocalSource @Inject constructor(
    private val mapleMPreferenceManager : MapleMPreferenceManager
) : MapleMLocalDataSource{

    override fun getMainOcid(): String {
        return mapleMPreferenceManager.getMainOcid()
    }

    override fun isFavoriteListEmpty(): Boolean {
        return mapleMPreferenceManager.isFavoriteListEmpty()
    }
}