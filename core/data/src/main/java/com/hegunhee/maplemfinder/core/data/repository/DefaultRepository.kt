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
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val localDataSource : LocalDataSource,
    private val mapleMRemoteDataSource: MapleMRemoteDataSource
) : Repository {

    private val mainOcid : Flow<String> = localDataSource.getMainOcid()
    private val favoriteOcids : Flow<Set<String>> = localDataSource.getFavoriteOcids()
    private val historyOcids : Flow<Set<String>> = localDataSource.getHistoryOcids()
    override suspend fun getCharacterTotalInfo(name: String, worldName: String): Result<Character> {
        return runCatching {
            val ocid = mapleMRemoteDataSource.getCharacterOcid(name,worldName).id
            getCharacterTotalInfo(ocid).getOrThrow()
        }
    }

    override suspend fun getCharacterTotalInfo(ocid : String) : Result<Character> = coroutineScope{
        runCatching {
            val infoResponse = mapleMRemoteDataSource.getCharacterInfo(ocid = ocid)
            val characterInfo = infoResponse.toCharacterInfo()
            val characterDate = infoResponse.toCharacterDate()
            val characterItem = async{ mapleMRemoteDataSource.getCharacterItem(ocid).toItemList() }
            val characterStatus = async{ mapleMRemoteDataSource.getCharacterStatus(ocid).toStatusList() }
            val isMain = mainOcid.first() == ocid
            val isFavorite = favoriteOcids.first().contains(ocid)
            Character(
                ocid = ocid,
                name = infoResponse.characterName,
                world = worldNameToWorld(infoResponse.worldName),
                date = characterDate,
                equippedItemList = characterItem.await(),
                info = characterInfo,
                statusList = characterStatus.await(),
                isMain = isMain,
                isFavorite = isFavorite
            )
        }
    }

    override fun getWorldList(): List<MapleMWorld> {
        return localDataSource.getWorldList()
    }

    override suspend fun updateMainOcid(ocid: String) {
        val currentMainOcid = mainOcid.first()
        localDataSource.updateMainOcid(
            if(currentMainOcid == ocid) {
                ""
            }else {
                ocid
            }
        )
    }

    override suspend fun getMainCharacter(): Result<Character> {
        val ocid = mainOcid.first()
        return if(ocid == "") {
            runCatching { Character.EMPTY }
        }else {
            getCharacterTotalInfo(ocid)
        }
    }

    override suspend fun toggleFavoriteOcid(ocid: String) {
        val currentFavoriteOcids = favoriteOcids.first()
        val isOcidContains = currentFavoriteOcids.contains(ocid)
        localDataSource.toggleFavoriteOcid(
            if(isOcidContains) {
                currentFavoriteOcids - ocid
            }else {
                currentFavoriteOcids + ocid
            }
        )
    }

    override suspend fun getFavoriteCharacterList(): Result<List<Character>> {
        return runCatching {
            val currentFavoriteOcids = favoriteOcids.first()
            currentFavoriteOcids.map { ocid ->
                getCharacterTotalInfo(ocid).getOrThrow()
            }.toList()
        }
    }

    override suspend fun isFavoriteListEmpty(): Result<Boolean> {
        return runCatching{ favoriteOcids.first().isEmpty() }
    }

    override suspend fun getHistoryCharacterList(): Result<List<CharacterSearch>> {
        val ocidList = historyOcids.first()
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

    override suspend fun toggleHistoryOcid(ocid: String) {
        val currentHistoryOcids = historyOcids.first()
        val isOcidContains = currentHistoryOcids.contains(ocid)
        localDataSource.toggleHistoryOcid(
            if(isOcidContains) {
                currentHistoryOcids - ocid
            }else {
                currentHistoryOcids + ocid
            }
        )
    }
}