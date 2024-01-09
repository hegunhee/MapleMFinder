package com.hegunhee.maplemfinder.core.domain.usecase

import com.hegunhee.maplemfinder.core.domain.repository.Repository
import javax.inject.Inject

class DeleteHistoryOcidUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(ocid : String) {
        repository.deleteHistoryOcid(ocid)
    }
}