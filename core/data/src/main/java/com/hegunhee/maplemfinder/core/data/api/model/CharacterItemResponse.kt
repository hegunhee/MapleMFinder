package com.hegunhee.maplemfinder.core.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterItemResponse(
    @Json(name = "item_equipment")val itemEquipmentResponseList : List<ItemEquipmentResponse>
)