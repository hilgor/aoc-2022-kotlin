package de.iits.aoc._2022

import getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day19Test {

    private val sut = Day19(getInputForDay(19))

    @Test
    fun testExample() {
        val result = sut.calculate()
        assertEquals(33, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2()

        assertEquals(62 * 56, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = Day19(getInputForDay(19, false)).calculate()
        assertEquals(1199, result)
        println(result)
    }

    @Test
    fun testInput2() {
        val result = Day19(getInputForDay(19, false)).calculate2()
        println(result)
    }
}