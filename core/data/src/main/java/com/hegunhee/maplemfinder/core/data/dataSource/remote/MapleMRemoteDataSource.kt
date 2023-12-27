package com.hegunhee.maplemfinder.core.data.dataSource.remote

import com.hegunhee.maplemfinder.core.data.api.model.CharacterInfoResponse
import com.hegunhee.maplemfinder.core.data.api.model.CharacterItemResponse
import com.hegunhee.maplemfinder.core.data.api.model.CharacterStatusResponse
import com.hegunhee.maplemfinder.core.data.api.model.OcidResponse

interface MapleMRemoteDataSource {

    suspend fun getCharacterOcid(name : String, worldName : String) : OcidResponse

    suspend fun getCharacterInfo(ocid : String) : CharacterInfoResponse

    suspend fun getCharacterStatus(ocid : String) : CharacterStatusResponse

    suspend fun getCharacterItem(ocid : String) : CharacterItemResponse
}