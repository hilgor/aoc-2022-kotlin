package de.iits.aoc._2022

import de.iits.aoc.util.getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day06Test {

    private val sut = Day06()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(6))
        assertEquals(7, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate(getInputForDay(6), 14)

        assertEquals(19, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result =sut.calculate(getInputForDay(6, false))
        println(result)
        assertEquals(1034, result)
    }

    @Test
    fun testInput2() {
        val result = sut.calculate(getInputForDay(6, false), 14)

        println(result)
        assertEquals(2472, result)
    }
}