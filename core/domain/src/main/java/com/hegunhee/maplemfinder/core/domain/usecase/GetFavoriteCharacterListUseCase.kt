package com.hegunhee.maplemfinder.core.domain.usecase

import com.hegunhee.maplefinder.core.model.Character
import com.hegunhee.maplemfinder.core.domain.repository.Repository
import javax.inject.Inject

class GetFavoriteCharacterListUseCase @Inject constructor(
    private val repository: Repository
){

    suspend operator fun invoke() : Result<List<Character>> {
        return repository.getFavoriteCharacterList()
    }
}