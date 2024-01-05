package com.hegunhee.maplemfinder.core.domain.usecase

import com.hegunhee.maplemfinder.core.domain.repository.Repository
import javax.inject.Inject

class GetWorldIconUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(name : String) : Int {
        return repository.getWorldIcon(name = name)
    }
}