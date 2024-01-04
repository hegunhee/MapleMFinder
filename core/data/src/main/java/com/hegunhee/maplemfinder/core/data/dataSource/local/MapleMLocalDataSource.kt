package com.hegunhee.maplemfinder.core.data.dataSource.local

interface MapleMLocalDataSource {

    fun getMainOcid() : String

    fun isFavoriteListEmpty() : Boolean
}