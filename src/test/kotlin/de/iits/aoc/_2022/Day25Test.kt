package de.iits.aoc._2022

import getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day25Test {

    private val sut = Day25()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(25))
        assertEquals("2=-1=0", result)
    }

    ///
    ///


    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(25, false))
        assertEquals("2=020-===0-1===2=020", result)
        println(result)
    }

    @Test
    fun parseSnafu() {
        assertEquals(1, sut.parseSnafu("1"))
        assertEquals(2, sut.parseSnafu("2"))
        assertEquals(3, sut.parseSnafu("1="))
        assertEquals(4, sut.parseSnafu("1-"))
        assertEquals(5, sut.parseSnafu("10"))
        assertEquals(6, sut.parseSnafu("11"))
        assertEquals(7, sut.parseSnafu("12"))
        assertEquals(8, sut.parseSnafu("2="))
        assertEquals(9, sut.parseSnafu("2-"))
        assertEquals(10, sut.parseSnafu("20"))
        assertEquals(13, sut.parseSnafu("1=="))
        assertEquals(14, sut.parseSnafu("1=-"))
        assertEquals(15, sut.parseSnafu("1=0"))
        assertEquals(16, sut.parseSnafu("1=1"))
        assertEquals(17, sut.parseSnafu("1=2"))
        assertEquals(18, sut.parseSnafu("1-="))
        assertEquals(19, sut.parseSnafu("1--"))
        assertEquals(20, sut.parseSnafu("1-0"))
        assertEquals(21, sut.parseSnafu("1-1"))
        assertEquals(22, sut.parseSnafu("1-2"))
        assertEquals(23, sut.parseSnafu("10="))
        assertEquals(24, sut.parseSnafu("10-"))
        assertEquals(25, sut.parseSnafu("100"))
        assertEquals(2022, sut.parseSnafu("1=11-2"))
        assertEquals(12345, sut.parseSnafu("1-0---0"))
        assertEquals(314159265, sut.parseSnafu("1121-1110-1=0"))
        assertEquals(1747, sut.parseSnafu("1=-0-2"))
        assertEquals(906, sut.parseSnafu("12111"))
        assertEquals(198, sut.parseSnafu("2=0="))
        assertEquals(11, sut.parseSnafu("21"))
        assertEquals(201, sut.parseSnafu("2=01"))
        assertEquals(31, sut.parseSnafu("111"))
        assertEquals(1257, sut.parseSnafu("20012"))
        assertEquals(32, sut.parseSnafu("112"))
        assertEquals(353, sut.parseSnafu("1=-1="))

    }

    @Test
    fun convertToSnafu() {
        assertEquals(sut.convertToSnafu(1), "1")
        assertEquals(sut.convertToSnafu(2), "2")
        assertEquals(sut.convertToSnafu(3), "1=")
        assertEquals(sut.convertToSnafu(4), "1-")
        assertEquals(sut.convertToSnafu(5), "10")
        assertEquals(sut.convertToSnafu(6), "11")
        assertEquals(sut.convertToSnafu(7), "12")
        assertEquals(sut.convertToSnafu(8), "2=")
        assertEquals(sut.convertToSnafu(9), "2-")
        assertEquals(sut.convertToSnafu(10), "20")
        assertEquals(sut.convertToSnafu(13), "1==")
        assertEquals(sut.convertToSnafu(14), "1=-")
        assertEquals(sut.convertToSnafu(15), "1=0")
        assertEquals(sut.convertToSnafu(16), "1=1")
        assertEquals(sut.convertToSnafu(17), "1=2")
        assertEquals(sut.convertToSnafu(18), "1-=")
        assertEquals(sut.convertToSnafu(19), "1--")
        assertEquals(sut.convertToSnafu(20), "1-0")
        assertEquals(sut.convertToSnafu(21), "1-1")
        assertEquals(sut.convertToSnafu(22), "1-2")
        assertEquals(sut.convertToSnafu(23), "10=")
        assertEquals(sut.convertToSnafu(24), "10-")
        assertEquals(sut.convertToSnafu(25), "100")
        assertEquals(sut.convertToSnafu(2022), "1=11-2")
        assertEquals(sut.convertToSnafu(12345), "1-0---0")
        assertEquals(sut.convertToSnafu(314159265), "1121-1110-1=0")
        assertEquals(sut.convertToSnafu(1747), "1=-0-2")
        assertEquals(sut.convertToSnafu(906), "12111")
        assertEquals(sut.convertToSnafu(198), "2=0=")
        assertEquals(sut.convertToSnafu(11), "21")
        assertEquals(sut.convertToSnafu(201), "2=01")
        assertEquals(sut.convertToSnafu(31), "111")
        assertEquals(sut.convertToSnafu(1257), "20012")
        assertEquals(sut.convertToSnafu(32), "112")
        assertEquals(sut.convertToSnafu(353), "1=-1=")
    }
}