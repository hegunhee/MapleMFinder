package com.hegunhee.maplemfinder.core.domain.usecase

import com.hegunhee.maplemfinder.core.domain.repository.Repository
import javax.inject.Inject

class AddHistoryOcidUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(ocid : String) {
        repository.addHistoryOcid(ocid)
    }
}