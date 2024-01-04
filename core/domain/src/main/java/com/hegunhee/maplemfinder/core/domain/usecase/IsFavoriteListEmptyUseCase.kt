package com.hegunhee.maplemfinder.core.domain.usecase

import com.hegunhee.maplemfinder.core.domain.repository.Repository
import javax.inject.Inject

class IsFavoriteListEmptyUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke() : Result<Boolean> {
        return repository.isFavoriteListEmpty()
    }
}