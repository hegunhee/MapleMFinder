package com.hegunhee.maplemfinder.core.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StatusResponse(
    @Json(name = "stat_name")val name: String,
    @Json(name = "stat_value")val value: String
)