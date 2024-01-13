package com.hegunhee.maplemfinder.core.data.dataSource.local

import com.hegunhee.maplefinder.core.model.MapleMWorld

interface LocalDataSource {

    fun getMainOcid() : String

    fun setMainOcid(coid : String)

    fun isMainOcid(ocid : String) : Boolean

    fun isFavoriteListEmpty() : Boolean

    fun getWorldList() : List<MapleMWorld>

    fun getFavoriteOcidList() : List<String>

    fun isFavoriteOcid(ocid : String) : Boolean

    fun toggleFavoriteOcid(ocid : String)

    fun getHistoryOcidList() : List<String>

    fun addHistoryOcid(ocid : String)

    fun deleteHistoryOcid(ocid : String)
}