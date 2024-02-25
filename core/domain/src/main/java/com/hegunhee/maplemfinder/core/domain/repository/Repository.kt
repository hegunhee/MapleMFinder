package com.hegunhee.maplemfinder.core.domain.repository

import com.hegunhee.maplefinder.core.model.Character
import com.hegunhee.maplefinder.core.model.CharacterSearch
import com.hegunhee.maplefinder.core.model.MapleMWorld
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getCharacterTotalInfo(name : String, worldName : String) : Result<Character>

    suspend fun getCharacterTotalInfo(ocid : String) : Result<Character>

    fun getWorldList() : List<MapleMWorld>

    fun setMainOcid(ocid : String)

    suspend fun getMainCharacter() : Result<Character>

    suspend fun toggleFavoriteOcid(ocid : String)

    suspend fun getHistoryCharacterList() : Result<List<CharacterSearch>>

    fun addHistoryOcid(ocid : String)
    suspend fun getFavoriteCharacterList() : Result<List<Character>>

    fun deleteHistoryOcid(ocid : String)
    suspend fun isFavoriteListEmpty() : Result<Boolean>


}