package com.hegunhee.maplemfinder.core.data.dataSource.local

import com.hegunhee.maplefinder.core.model.MapleMWorld
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getMainOcid() : String

    fun setMainOcid(coid : String)

    fun isMainOcid(ocid : String) : Boolean

    fun getWorldList() : List<MapleMWorld>

    fun getFavoriteOcids() : Flow<Set<String>>

    suspend fun toggleFavoriteOcid(favoriteOcids : Set<String>)

    fun getHistoryOcids() : Flow<Set<String>>

    suspend fun toggleHistoryOcid(historyOcids : Set<String>)
}