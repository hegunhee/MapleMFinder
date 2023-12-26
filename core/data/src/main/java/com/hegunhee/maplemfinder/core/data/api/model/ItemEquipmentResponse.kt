package com.hegunhee.maplemfinder.core.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemEquipmentResponse(
    @Json(name = "item_equipment_page_name")val pageName: String,
    @Json(name = "item_equipment_slot_name")val slotName: String,
    @Json(name = "item_name")val itemName: String
)