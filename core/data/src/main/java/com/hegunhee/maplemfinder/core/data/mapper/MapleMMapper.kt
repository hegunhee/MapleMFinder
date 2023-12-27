package com.hegunhee.maplemfinder.core.data.mapper

import com.hegunhee.maplefinder.core.model.mapleM.CharacterDate
import com.hegunhee.maplefinder.core.model.mapleM.CharacterInfo
import com.hegunhee.maplefinder.core.model.mapleM.CharacterStatus
import com.hegunhee.maplefinder.core.model.mapleM.Item
import com.hegunhee.maplefinder.core.model.mapleM.ItemGrade
import com.hegunhee.maplemfinder.core.data.api.model.CharacterInfoResponse
import com.hegunhee.maplemfinder.core.data.api.model.CharacterItemResponse
import com.hegunhee.maplemfinder.core.data.api.model.CharacterStatusResponse

fun CharacterInfoResponse.toCharacterInfo() : CharacterInfo {
    return CharacterInfo(
        gender = gender,
        jobName = jobName,
        exp = exp,
        level = level
    )
}

fun CharacterInfoResponse.toCharacterDate() : CharacterDate {
    return CharacterDate(
        createDate = createDate,
        lastLoginDate = lastLoginDate,
        lastLogoutDate = lastLogoutDate
    )
}

fun CharacterItemResponse.toItemList() : List<Item> {
    val itemList = this.itemEquipmentResponseList
    return itemList.map {
        Item(
            slotName = it.slotName,
            itemName = it.itemName,
            grade = it.itemName.toItemGrade()
        )
    }
}

fun CharacterStatusResponse.toStatusList() : List<CharacterStatus> {
    val statusList = this.statusList
    return statusList.map {
        CharacterStatus(
            name = it.name,
            value = it.value
        )
    }
}

private fun String.toItemGrade() : ItemGrade {
    return when(this.split(" ").last()) {
        "(Cash)" -> ItemGrade.Cash
        "[레어]" -> ItemGrade.Rare
        "[에픽]" -> ItemGrade.Epic
        "[유니크]" -> ItemGrade.Unique
        "[레전더리]" -> ItemGrade.Legendary
        else -> ItemGrade.Empty
    }
}