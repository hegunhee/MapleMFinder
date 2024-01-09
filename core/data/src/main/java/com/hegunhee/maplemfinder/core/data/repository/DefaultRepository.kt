package com.hegunhee.maplemfinder.core.data.repository

import com.hegunhee.maplefinder.core.model.mapleM.Character
import com.hegunhee.maplefinder.core.model.mapleM.CharacterSearch
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
            getCharacterTotalInfo(ocid).getOrThrow()
        }
    }

    override suspend fun getCharacterTotalInfo(ocid : String) : Result<Character> {
        return runCatching {
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

    override fun setMainOcid(ocid: String) {
        mapleMLocalDataSource.setMainOcid(ocid)
    }

    override suspend fun getMainCharacter(): Result<Character> {
        val ocid = mapleMLocalDataSource.getMainOcid()
        return if(ocid == "") {
            runCatching { Character.EMPTY }
        }else {
            getCharacterTotalInfo(ocid)
        }
    }

    override fun toggleFavoriteOcid(ocid: String) {
        mapleMLocalDataSource.toggleFavoriteOcid(ocid)
    }

    override suspend fun getHistoryCharacterList(): Result<List<CharacterSearch>> {
        val ocidList = mapleMLocalDataSource.getHistoryOcidList()
        if(ocidList.isEmpty()) {
            return Result.success(emptyList())
        }
        return runCatching {
            ocidList.map { ocid ->
                val info = mapleMRemoteDataSource.getCharacterInfo(ocid)
                CharacterSearch(ocid,info.characterName, worldNameToWorld(info.worldName))
            }
        }
    }

    override fun addHistoryOcid(ocid: String) {
        mapleMLocalDataSource.addHistoryOcid(ocid)
    }

    override fun deleteHistoryOcid(ocid: String) {
        mapleMLocalDataSource.deleteHistoryOcid(ocid)
    }

    override fun isFavoriteListEmpty(): Result<Boolean> {
        return runCatching{ mapleMLocalDataSource.isFavoriteListEmpty() }
    }


}