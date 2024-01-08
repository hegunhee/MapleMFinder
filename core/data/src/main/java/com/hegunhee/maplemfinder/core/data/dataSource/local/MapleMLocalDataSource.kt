package com.hegunhee.maplemfinder.core.data.dataSource.local

import com.hegunhee.maplefinder.core.model.mapleM.MapleMWorld

interface MapleMLocalDataSource {

    fun getMainOcid() : String

    fun setMainOcid(coid : String)

    fun isMainOcid(ocid : String) : Boolean

    fun isFavoriteListEmpty() : Boolean

    fun getWorldList() : List<MapleMWorld>

    fun isFavoriteOcid(ocid : String) : Boolean

    fun toggleFavoriteOcid(ocid : String)

    fun getWorldIcon(name : String) : Int
}