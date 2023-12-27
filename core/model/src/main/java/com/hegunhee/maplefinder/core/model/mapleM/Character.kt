package com.hegunhee.maplefinder.core.model.mapleM

data class Character(
    val ocid : String,
    val name : String,
    val worldName : String,
    val info : CharacterInfo,
    val date : CharacterDate,
    val statusList : List<CharacterStatus>,
    val equippedItemList : List<Item>
)