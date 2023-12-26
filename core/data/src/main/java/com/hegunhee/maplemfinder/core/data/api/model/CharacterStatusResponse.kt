package com.hegunhee.maplemfinder.core.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterStatusResponse(
    @Json(name = "stat") val statusList: List<StatusResponse>
)