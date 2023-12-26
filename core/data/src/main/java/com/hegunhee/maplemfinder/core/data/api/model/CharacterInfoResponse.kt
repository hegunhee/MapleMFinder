package com.hegunhee.maplemfinder.core.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterInfoResponse(
    @Json(name = "character_name") val characterName: String,
    @Json(name = "world_name") val worldName: String,
    @Json(name = "character_date_create") val createDate: String?,
    @Json(name = "character_date_last_login") val lastLoginDate: String,
    @Json(name = "character_date_last_logout") val lastLogoutDate: String,
    @Json(name = "character_gender") val gender: String,
    @Json(name = "character_job_name") val jobName: String,
    @Json(name = "character_exp") val exp: Long,
    @Json(name = "character_level") val level: Int,
)