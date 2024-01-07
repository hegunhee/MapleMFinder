package com.hegunhee.maplemfinder.core.data.repository

import com.hegunhee.maplefinder.core.model.mapleM.Character
import com.hegunhee.maplefinder.core.model.mapleM.MapleMWorld
import com.hegunhee.maplemfinder.core.data.dataSource.local.MapleMLocalDataSource
import com.hegunhee.maplemfinder.core.data.dataSource.remote.MapleMRemoteDataSource
import com.hegunhee.maplemfinder.core.data.mapper.toCharacterDate
import com.hegunhee.maplemfinder.core.data.mapper.toCharacterInfo
import com.hegunhee.maplemfinder.core.data.mapper.toItemList
import com.hegunhee.maplemfinder.core.data.mapper.toStatusList
import com.hegunhee.maplemfinder.core.data.mapper.worldNameToWorld
import com.hegunhee.maplemfinder.core.domain.repository.Repository
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val mapleMLocalDataSource : MapleMLocalDataSource,
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
            val isMain = mapleMLocalDataSource.isMainOcid(ocid)
            val isFavorite = mapleMLocalDataSource.isFavoriteOcid(ocid)
            Character(
                ocid = ocid,
                name = infoResponse.characterName,
                world = worldNameToWorld(infoResponse.worldName),
                date = characterDate,
                equippedItemList = characterItem,
                info = characterInfo,
                statusList = characterStatus,
                isMain = isMain,
                isFavorite = isFavorite
            )
        }
    }

    override fun getWorldList(): List<MapleMWorld> {
        return mapleMLocalDataSource.getWorldList()
    }

    override fun getWorldIcon(name: String): Int {
        return mapleMLocalDataSource.getWorldIcon(name = name)
    }

    override suspend fun getMainCharacter(): Result<Character> {
        val ocid = mapleMLocalDataSource.getMainOcid()
        return if(ocid == "") {
            runCatching { Character.EMPTY }
        }else {
            runCatching {
                val infoResponse = mapleMRemoteDataSource.getCharacterInfo(ocid = ocid)
                getCharacterTotalInfo(infoResponse.characterName,infoResponse.worldName).getOrThrow()
            }
        }
    }

    override fun isFavoriteListEmpty(): Result<Boolean> {
        return runCatching{ mapleMLocalDataSource.isFavoriteListEmpty() }
    }


}