package com.hegunhee.maplemfinder.core.domain.usecase

import com.hegunhee.maplefinder.core.model.mapleM.Character
import com.hegunhee.maplemfinder.core.domain.repository.Repository
import javax.inject.Inject

class GetCharacterByOcidUseCase @Inject constructor(
    private val repository: Repository
){

    suspend operator fun invoke(ocid : String) : Result<Character> {
        return repository.getCharacterTotalInfo(ocid)
    }
}