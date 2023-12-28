package com.hegunhee.maplemfinder.core.data.repository

import com.hegunhee.maplefinder.core.model.mapleM.Character
import com.hegunhee.maplefinder.core.model.mapleM.worldList
import com.hegunhee.maplemfinder.core.data.dataSource.remote.MapleMRemoteDataSource
import com.hegunhee.maplemfinder.core.data.mapper.toCharacterDate
import com.hegunhee.maplemfinder.core.data.mapper.toCharacterInfo
import com.hegunhee.maplemfinder.core.data.mapper.toItemList
import com.hegunhee.maplemfinder.core.data.mapper.toStatusList
import com.hegunhee.maplemfinder.core.domain.repository.Repository
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val mapleMRemoteDataSource: MapleMRemoteDataSource
) : Repository {
    override suspend fun getCharacterTotalInfo(name: String, worldName: String): Result<Character> {
        return runCatching {
            val ocid = mapleMRemoteDataSource.getCharacterOcid(name,worldName).id
            val infoResponse = mapleMRemoteDataSource.getCharacterInfo(ocid = ocid)
            val characterInfo = infoResponse.toCharacterInfo()
            val characterDate = infoResponse.toCharacterDate()
            val characterItem = mapleMRemoteDataSource.getCharacterItem(ocid).toItemList()
            val characterStatus = mapleMRemoteDataSource.getCharacterStatus(ocid).toStatusList()
            Character(
                ocid = ocid,
                name = infoResponse.characterName,
                worldName = infoResponse.worldName,
                date = characterDate,
                equippedItemList = characterItem,
                info = characterInfo,
                statusList = characterStatus
            )
        }
    }

    override fun getWorldNameList(): List<String> = worldList


}