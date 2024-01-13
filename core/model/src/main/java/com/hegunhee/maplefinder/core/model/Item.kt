package com.hegunhee.maplefinder.core.model

data class Item(
    val slotName : String,
    val itemName : String,
    val grade : ItemGrade
)

sealed interface ItemGrade {

    object Empty : ItemGrade

    object Cash : ItemGrade

    object Rare : ItemGrade

    object Epic : ItemGrade

    object Unique : ItemGrade

    object Legendary : ItemGrade
}