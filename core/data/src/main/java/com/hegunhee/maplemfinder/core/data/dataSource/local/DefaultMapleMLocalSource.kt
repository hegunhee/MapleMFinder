package com.hegunhee.maplemfinder.core.data.dataSource.local

import com.hegunhee.maplefinder.core.model.mapleM.MapleMWorld
import com.hegunhee.maplemfinder.core.data.R
import javax.inject.Inject

class DefaultMapleMLocalSource @Inject constructor(
    private val mapleMPreferenceManager : MapleMPreferenceManager
) : MapleMLocalDataSource{

    private val worldList = listOf<MapleMWorld>(
        MapleMWorld(name = "아케인",icon = R.drawable.arcane),
        MapleMWorld(name = "크로아",icon = R.drawable.croa),
        MapleMWorld(name = "엘리시움",icon = R.drawable.elysium),
        MapleMWorld(name = "루나",icon = R.drawable.luna),
        MapleMWorld(name = "스카니아",icon = R.drawable.scania),
        MapleMWorld(name = "유니온",icon = R.drawable.union),
        MapleMWorld(name = "제니스",icon = R.drawable.zenith)
    )

    override fun getMainOcid(): String {
        return mapleMPreferenceManager.getMainOcid()
    }

    override fun isFavoriteListEmpty(): Boolean {
        return mapleMPreferenceManager.isFavoriteListEmpty()
    }

    override fun getWorldList(): List<MapleMWorld> {
        return worldList
    }

    override fun getWorldIcon(name: String): Int {
        return worldList.find { it.name == name }?.icon ?: R.drawable.ic_default_server_mark_24
    }
}