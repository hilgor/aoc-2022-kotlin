package de.iits.aoc._2022

import getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day08Test {

    private val sut = Day08()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(8))
        assertEquals(21, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2(getInputForDay(8))

        assertEquals(8, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(8,false))
        println(result)
        assertEquals(1801, result)
    }

    @Test
    fun testInput2() {
        val result = sut.calculate2(getInputForDay(8,false))
        println(result)
        assertEquals(209880, result)
    }
}