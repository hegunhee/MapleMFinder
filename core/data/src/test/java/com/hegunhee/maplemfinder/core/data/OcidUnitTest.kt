package com.hegunhee.maplemfinder.core.data

import com.hegunhee.maplemfinder.core.data.api.MapleMApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class OcidUnitTest {

    private lateinit var mapleMApi : MapleMApi
    @Before
    fun initMapleMApi() {
        mapleMApi = getMapleMApi()
    }

    // 정상적인 query값일경우
    @Test
    fun getMapleMOcidTest() {
        runBlocking {
            runCatching {
                mapleMApi.getOcid(
                    name = characterName,
                    world = worldName
                )
            }.onSuccess {
                assertEquals(requestOcid,it.id)
            }.onFailure {
                println(it.message)
                assert(false)
            }
        }
    }

    @Test
    fun getInvailedMapleMOcidTest() {
        runBlocking {
            runCatching {
                mapleMApi.getOcid(
                    name = invailedCharacterName,
                    world = worldName
                )
            }.onSuccess {
                assert(false)
            }.onFailure {
                println(it.message)
                assertEquals(invailedQueryResponse,it.message)
            }
        }
    }

    private val requestOcid = "9857d4a03a080b40cad682141ae3b0729c8695ba60dec42d9da8928f8ed9773c"

    private val invailedCharacterName = "ㅁ나어민어ㅏㅁ너이"
    private val invailedQueryResponse = "HTTP 400 "
}