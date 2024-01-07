package com.hegunhee.maplefinder.core.model.mapleM

data class Character(
    val ocid : String,
    val name : String,
    val world : MapleMWorld,
    val info : CharacterInfo,
    val date : CharacterDate,
    val statusList : List<CharacterStatus>,
    val equippedItemList : List<Item>
) {
    companion object {
        val EMPTY : Character= Character(
            ocid = "",
            name = "",
            world = MapleMWorld("",0),
            info = CharacterInfo("","",0L,0),
            date = CharacterDate("","",""),
            statusList = emptyList(),
            equippedItemList = emptyList()
        )
    }
}