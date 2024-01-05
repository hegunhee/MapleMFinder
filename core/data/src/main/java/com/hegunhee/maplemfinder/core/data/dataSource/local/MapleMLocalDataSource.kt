package com.hegunhee.maplemfinder.core.data.dataSource.local

import com.hegunhee.maplefinder.core.model.mapleM.MapleMWorld

interface MapleMLocalDataSource {

    fun getMainOcid() : String

    fun isFavoriteListEmpty() : Boolean

    fun getWorldList() : List<MapleMWorld>

    fun getWorldIcon(name : String) : Int
}