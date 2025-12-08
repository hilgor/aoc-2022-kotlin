package de.iits.aoc._2022

import de.iits.aoc.util.getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day04Test {

    private val sut = Day04()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(4))
        assertEquals(2, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2(getInputForDay(4))

        assertEquals(4, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(4, false))
        println(result)
        assertEquals(584, result)
    }

    @Test
    fun testInput2() {
        val result = sut.calculate2(getInputForDay(4, false))
        println(result)
        assertEquals(933, result)
    }
}