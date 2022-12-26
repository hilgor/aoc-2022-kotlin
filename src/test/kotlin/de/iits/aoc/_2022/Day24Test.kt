package de.iits.aoc._2022

import getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day24Test {

    private val sut = Day24()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(24))
        assertEquals(18, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate(getInputForDay(24), true)

        assertEquals(54, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(24, false))
        println(result)
        assertEquals(334, result)
    }

    @Test
    fun testInput2() {
        val result = sut.calculate(getInputForDay(24, false), true)
        println(result)
        assertEquals(934, result)
    }
}