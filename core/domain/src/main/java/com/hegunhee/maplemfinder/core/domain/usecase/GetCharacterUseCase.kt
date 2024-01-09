package com.hegunhee.maplemfinder.core.domain.usecase

import com.hegunhee.maplefinder.core.model.mapleM.Character
import com.hegunhee.maplemfinder.core.domain.repository.Repository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(name : String,worldName : String) : Result<Character> {
        return repository.getCharacterTotalInfo(name = name, worldName = worldName)
    }

    suspend operator fun invoke(ocid : String) : Result<Character> {
        return repository.getCharacterTotalInfo(ocid = ocid)
    }

}