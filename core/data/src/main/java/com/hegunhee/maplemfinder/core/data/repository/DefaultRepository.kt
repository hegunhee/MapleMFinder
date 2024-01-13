package com.hegunhee.maplemfinder.core.data.repository

import com.hegunhee.maplefinder.core.model.Character
import com.hegunhee.maplefinder.core.model.CharacterSearch
import com.hegunhee.maplefinder.core.model.MapleMWorld
import com.hegunhee.maplemfinder.core.data.dataSource.local.LocalDataSource
import com.hegunhee.maplemfinder.core.data.dataSource.remote.MapleMRemoteDataSource
import com.hegunhee.maplemfinder.core.data.mapper.toCharacterDate
import com.hegunhee.maplemfinder.core.data.mapper.toCharacterInfo
import com.hegunhee.maplemfinder.core.data.mapper.toItemList
import com.hegunhee.maplemfinder.core.data.mapper.toStatusList
import com.hegunhee.maplemfinder.core.data.mapper.worldNameToWorld
import com.hegunhee.maplemfinder.core.domain.repository.Repository
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val localDataSource : LocalDataSource,
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
            val isMain = localDataSource.isMainOcid(ocid)
            val isFavorite = localDataSource.isFavoriteOcid(ocid)
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
        return localDataSource.getWorldList()
    }

    override fun setMainOcid(ocid: String) {
        localDataSource.setMainOcid(ocid)
    }

    override suspend fun getMainCharacter(): Result<Character> {
        val ocid = localDataSource.getMainOcid()
        return if(ocid == "") {
            runCatching { Character.EMPTY }
        }else {
            getCharacterTotalInfo(ocid)
        }
    }

    override fun toggleFavoriteOcid(ocid: String) {
        localDataSource.toggleFavoriteOcid(ocid)
    }

    override suspend fun getHistoryCharacterList(): Result<List<CharacterSearch>> {
        val ocidList = localDataSource.getHistoryOcidList()
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
        localDataSource.addHistoryOcid(ocid)
    }

    override fun deleteHistoryOcid(ocid: String) {
        localDataSource.deleteHistoryOcid(ocid)
    }

    override suspend fun getFavoriteCharacterList(): Result<List<Character>> {
        return runCatching {
            localDataSource.getFavoriteOcidList().map { ocid ->
                getCharacterTotalInfo(ocid).getOrThrow()
            }
        }
    }

    override fun isFavoriteListEmpty(): Result<Boolean> {
        return runCatching{ localDataSource.isFavoriteListEmpty() }
    }


}