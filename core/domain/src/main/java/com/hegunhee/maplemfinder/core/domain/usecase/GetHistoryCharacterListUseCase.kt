package com.hegunhee.maplemfinder.core.domain.usecase

import com.hegunhee.maplefinder.core.model.mapleM.CharacterSearch
import com.hegunhee.maplemfinder.core.domain.repository.Repository
import javax.inject.Inject

class GetHistoryCharacterListUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke() : Result<List<CharacterSearch>> {
        return repository.getHistoryCharacterList()
    }
}