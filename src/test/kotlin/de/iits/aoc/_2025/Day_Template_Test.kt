package de.iits.aoc._2025

import de.iits.aoc.util.getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day_Template_Test {

    private val sut = Day01()
    private val day = 1

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(day, example = true, 2025))
        assertEquals(2, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2(getInputForDay(day, example = true, 2025))

        assertEquals(4, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(day, false, 2025))
        println(result)
    }

    @Test
    fun testInput2() {
        val result = sut.calculate2(getInputForDay(day, false, 2025))
        println(result)
    }
}