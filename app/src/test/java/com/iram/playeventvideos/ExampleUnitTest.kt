package com.iram.playeventvideos

import com.iram.playeventvideos.utils.DateFormat
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
    fun dateCheck(){
        assertEquals("true", DateFormat.getTomorrowsDate(DateFormat.stringToDate("2021-10-23T04:06:25.232Z")))
    }
}