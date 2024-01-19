package com.hegunhee.maplemfinder.core.data

import com.hegunhee.maplefinder.core.model.Character
import com.hegunhee.maplemfinder.core.data.api.MapleMApi
import com.hegunhee.maplemfinder.core.data.mapper.toCharacterDate
import com.hegunhee.maplemfinder.core.data.mapper.toCharacterInfo
import com.hegunhee.maplemfinder.core.data.mapper.toItemList
import com.hegunhee.maplemfinder.core.data.mapper.toStatusList
import com.hegunhee.maplemfinder.core.data.mapper.worldNameToWorld
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class GetCharacterTimeUnitTest {

    private lateinit var mapleMApi : MapleMApi

    @Before
    fun initMapleMApi() {
        mapleMApi = getMapleMApi()
    }

    // 순차적으로 API call시 시간이 얼마나 걸리는지 테스트
    // 걸린시간 : 126.355ms
    @OptIn(ExperimentalTime::class)
    @Test
    fun getCharacterBySequential() {
        runBlocking {
            runCatching {
                val ocid = mapleMApi.getOcid(name = characterName, world = worldName).id
                val infoResponse = mapleMApi.getCharacterInfo(ocid = ocid)
                val characterInfo = infoResponse.toCharacterInfo()
                val characterDate = infoResponse.toCharacterDate()
                measureTimedValue {
                    val characterItem = mapleMApi.getCharacterItem(ocid).toItemList()
                    val characterStatus = mapleMApi.getCharacterStatus(ocid).toStatusList()
                    val character = Character(
                        ocid = ocid,
                        name = infoResponse.characterName,
                        world = worldNameToWorld(infoResponse.worldName),
                        date = characterDate,
                        equippedItemList = characterItem,
                        info = characterInfo,
                        statusList = characterStatus,
                        isMain = true,
                        isFavorite = true
                    )
                    println(character.toString())
                }
            }.onSuccess {
                println("걸린시간 : ${it.duration}")
                assert(true)
            }.onFailure {
                println(it)
                assert(false)
            }
        }
    }
    
    // async로 API call시 시간이 얼마나 걸리는지 테스트
    // 걸린시간 : 99.495800ms
    @OptIn(ExperimentalTime::class)
    @Test
    fun getCharacterByAsync() {
        runBlocking {
            runCatching {
                val ocid = mapleMApi.getOcid(name = characterName, world = worldName).id
                val infoResponse = mapleMApi.getCharacterInfo(ocid = ocid)
                val characterInfo = infoResponse.toCharacterInfo()
                val characterDate = infoResponse.toCharacterDate()
                measureTimedValue {
                    val characterItem = async{ mapleMApi.getCharacterItem(ocid).toItemList() }
                    val characterStatus = async{ mapleMApi.getCharacterStatus(ocid).toStatusList() }
                    val character = Character(
                        ocid = ocid,
                        name = infoResponse.characterName,
                        world = worldNameToWorld(infoResponse.worldName),
                        date = characterDate,
                        equippedItemList = characterItem.await(),
                        info = characterInfo,
                        statusList = characterStatus.await(),
                        isMain = true,
                        isFavorite = true
                    )
                    println(character.toString())
                }
            }.onSuccess {
                println("걸린시간 : ${it.duration}")
                assert(true)
            }.onFailure {
                println(it)
                assert(false)
            }
        }
    }

}