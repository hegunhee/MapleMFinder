package com.hegunhee.maplemfinder.core.data.dataSource.local

import com.hegunhee.maplefinder.core.model.mapleM.MapleMWorld
import com.hegunhee.maplemfinder.core.data.R
import javax.inject.Inject

class DefaultMapleMLocalSource @Inject constructor(
    private val mapleMPreferenceManager : MapleMPreferenceManager
) : MapleMLocalDataSource{

    private val serverList = listOf<MapleMWorld>(
        MapleMWorld(name = "아케인",icon = R.drawable.arcane),
        MapleMWorld(name = "크로아",icon = R.drawable.croa),
        MapleMWorld(name = "엘리시움",icon = R.drawable.elysium),
        MapleMWorld(name = "루나",icon = R.drawable.Luna),
        MapleMWorld(name = "스카니아",icon = R.drawable.Scania),
        MapleMWorld(name = "유니온",icon = R.drawable.union),
        MapleMWorld(name = "제니스",icon = R.drawable.Zenith)
    )

    override fun getMainOcid(): String {
        return mapleMPreferenceManager.getMainOcid()
    }

    override fun isFavoriteListEmpty(): Boolean {
        return mapleMPreferenceManager.isFavoriteListEmpty()
    }

    override fun getServerList(): List<MapleMWorld> {
        return serverList
    }

    override fun getServerIcon(name: String): Int {
        return serverList.find { it.name == name }?.icon ?: R.drawable.ic_default_server_mark_24
    }
}