package com.hegunhee.maplemfinder.core.data.api

import com.hegunhee.maplemfinder.core.data.api.model.OcidResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MapleMApi {

    @GET("v1/id")
    suspend fun getOcid(
        @Query("character_name") name : String,
        @Query("world_name") world : String
    ) : OcidResponse

}