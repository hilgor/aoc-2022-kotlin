package de.iits.aoc._2022

import getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day21Test {

    private val sut = Day21(getInputForDay(21))

    @Test
    fun testExample() {
        val result = sut.calculate()
        assertEquals(152, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2()

        assertEquals(301, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = Day21(getInputForDay(21,false)).calculate()
        println(result)
        assertEquals(157714751182692, result)
    }

    @Test
    fun testInput2() {
        val result = Day21(getInputForDay(21, false)).calculate2()
        println(result)
        assertEquals(3373767893067, result)
    }
}