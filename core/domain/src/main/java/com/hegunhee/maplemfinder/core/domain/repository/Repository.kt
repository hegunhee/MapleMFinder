package com.hegunhee.maplemfinder.core.domain.repository

import com.hegunhee.maplefinder.core.model.mapleM.Character
import com.hegunhee.maplefinder.core.model.mapleM.CharacterSearch
import com.hegunhee.maplefinder.core.model.mapleM.MapleMWorld

interface Repository {

    suspend fun getCharacterTotalInfo(name : String, worldName : String) : Result<Character>

    suspend fun getCharacterTotalInfo(ocid : String) : Result<Character>

    fun getWorldList() : List<MapleMWorld>

    fun setMainOcid(ocid : String)

    fun getWorldIcon(name : String) : Int

    suspend fun getMainCharacter() : Result<Character>

    fun toggleFavoriteOcid(ocid : String)

    suspend fun getHistoryCharacterList() : Result<List<CharacterSearch>>

    fun addHistoryOcid(ocid : String)

    fun deleteHistoryOcid(ocid : String)

    fun isFavoriteListEmpty() : Result<Boolean>
}