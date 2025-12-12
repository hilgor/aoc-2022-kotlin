package de.iits.aoc._2025

import de.iits.aoc.util.getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day10Test {

    private val sut = Day10()
    private val day = 10

    @Test
    fun testExample() {
        val result = sut.calculate1(getInputForDay(day, example = true, 2025))
        assertEquals(7, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2(getInputForDay(day, example = true, 2025))

        assertEquals(33, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(day, false, 2025))
        println(result) //466
    }

    @Test
    fun testInput2() {
        val result = sut.calculate2(getInputForDay(day, false, 2025))
        println(result)
    }
}