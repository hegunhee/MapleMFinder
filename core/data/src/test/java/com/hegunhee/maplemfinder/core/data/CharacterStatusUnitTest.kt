package com.hegunhee.maplemfinder.core.data

import com.hegunhee.maplemfinder.core.data.api.MapleMApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
class CharacterStatusUnitTest {

    private lateinit var mapleMApi : MapleMApi
    @Before
    fun initMapleMApi() {
        mapleMApi = getMapleMApi()
    }

    @Test
    fun getCharacterStatus() {
        runBlocking {
            runCatching {
                val ocid = mapleMApi.getOcid(
                    name = characterName,
                    world = worldName
                ).id
                mapleMApi.getCharacterStatus(
                    ocid = ocid
                )
            }.onSuccess {
                println(it.toString())
                assert(true)
            }.onFailure {
                println(it.toString())
                assert(false)
            }
        }
    }
}