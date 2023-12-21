package com.hegunhee.maplemfinder.core.data

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testApiKey() {
        val apiKey = BuildConfig.API_KEY
        assertEquals("Insert Your Api Key",apiKey)
    }
}