package com.hegunhee.maplemfinder.core.data

import com.hegunhee.maplefinder.core.model.MapleMWorld
import com.hegunhee.maplemfinder.core.data.mapper.worldList
import com.hegunhee.maplemfinder.core.data.mapper.worldNameToWorld
import org.junit.Test

class WorldMapUnitTest {

    @Test
    fun mapleWorldListToMapTest(){
        val list = worldList
        val a = list.map { worldNameToWorld(it.name) }
        if(a == list){
            assert(true)
        }else{
            assert(false)
        }
    }

    @Test
    fun falseWorldMapNameTest() {
        val unknownMap = MapleMWorld(name = "Unknown",icon = R.drawable.ic_default_server_mark_24)
        val returnMap = worldNameToWorld(unknownMap.name)
        if(unknownMap == returnMap) {
            assert(true)
        }else {
            assert(false)
        }

    }
}