package com.hegunhee.maplemfinder.core.domain.repository

import com.hegunhee.maplefinder.core.model.mapleM.Character

interface Repository {

    suspend fun getCharacterTotalInfo(name : String, worldName : String) : Result<Character>

    fun getWorldNameList() : List<String>
}