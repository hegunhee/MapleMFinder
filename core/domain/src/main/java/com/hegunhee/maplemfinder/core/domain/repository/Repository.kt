package com.hegunhee.maplemfinder.core.domain.repository

import com.hegunhee.maplefinder.core.model.Character
import com.hegunhee.maplefinder.core.model.CharacterSearch
import com.hegunhee.maplefinder.core.model.MapleMWorld

interface Repository {

    suspend fun getCharacterTotalInfo(name : String, worldName : String) : Result<Character>

    suspend fun getCharacterTotalInfo(ocid : String) : Result<Character>

    fun getWorldList() : List<MapleMWorld>

    suspend fun updateMainOcid(ocid : String)

    suspend fun getMainCharacter() : Result<Character>

    suspend fun toggleFavoriteOcid(ocid : String)

    suspend fun getFavoriteCharacterList() : Result<List<Character>>

    suspend fun isFavoriteListEmpty() : Result<Boolean>

    suspend fun getHistoryCharacterList() : Result<List<CharacterSearch>>

    suspend fun toggleHistoryOcid(ocid : String)
}