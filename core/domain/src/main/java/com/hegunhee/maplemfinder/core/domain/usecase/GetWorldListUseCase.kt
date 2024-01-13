package com.hegunhee.maplemfinder.core.domain.usecase

import com.hegunhee.maplefinder.core.model.MapleMWorld
import com.hegunhee.maplemfinder.core.domain.repository.Repository
import javax.inject.Inject

class GetWorldListUseCase @Inject constructor(
    private val repository: Repository
) {

     operator fun invoke() : List<MapleMWorld> {
         return repository.getWorldList()
     }
}