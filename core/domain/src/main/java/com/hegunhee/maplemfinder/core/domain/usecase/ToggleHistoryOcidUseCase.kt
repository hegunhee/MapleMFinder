package com.hegunhee.maplemfinder.core.domain.usecase

import com.hegunhee.maplemfinder.core.domain.repository.Repository
import javax.inject.Inject

class ToggleHistoryOcidUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(ocid : String) {
        repository.toggleHistoryOcid(ocid)
    }
}