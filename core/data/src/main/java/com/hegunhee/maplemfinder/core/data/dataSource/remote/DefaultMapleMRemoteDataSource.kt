package com.hegunhee.maplemfinder.core.data.dataSource.remote

import com.hegunhee.maplemfinder.core.data.api.MapleMApi
import com.hegunhee.maplemfinder.core.data.api.model.CharacterInfoResponse
import com.hegunhee.maplemfinder.core.data.api.model.CharacterItemResponse
import com.hegunhee.maplemfinder.core.data.api.model.CharacterStatusResponse
import com.hegunhee.maplemfinder.core.data.api.model.OcidResponse
import javax.inject.Inject

class DefaultMapleMRemoteDataSource @Inject constructor(
    private val mapleMApi: MapleMApi
): MapleMRemoteDataSource{
    override suspend fun getCharacterOcid(name: String, worldName: String): OcidResponse {
        return mapleMApi.getOcid(name,worldName)
    }

    override suspend fun getCharacterInfo(ocid: String): CharacterInfoResponse {
        return mapleMApi.getCharacterInfo(ocid)
    }

    override suspend fun getCharacterStatus(ocid: String): CharacterStatusResponse {
        return mapleMApi.getCharacterStatus(ocid)
    }

    override suspend fun getCharacterItem(ocid: String): CharacterItemResponse {
        return mapleMApi.getCharacterItem(ocid)
    }
}