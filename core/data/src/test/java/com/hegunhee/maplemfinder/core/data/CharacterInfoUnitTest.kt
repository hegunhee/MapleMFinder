package com.hegunhee.maplemfinder.core.data

import com.hegunhee.maplemfinder.core.data.api.MapleMApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class CharacterInfoUnitTest {

    private lateinit var mapleMApi : MapleMApi
    @Before
    fun initMapleMApi() {
        mapleMApi = getMapleMApi()
    }

    // 캐릭터명 : 지존
    // 서버명 : 아케인
    @Test
    fun getCharacterInfo() {
        runBlocking {
            runCatching {
                val ocid = mapleMApi.getOcid(
                    name = characterName,
                    world = worldName
                ).id
                mapleMApi.getCharacterInfo(
                    ocid = ocid
                )
            }.onSuccess {
                println(it.toString())
                assert(true)
            }.onFailure {
                println(it.message)
                assert(false)
            }
        }
    }
}