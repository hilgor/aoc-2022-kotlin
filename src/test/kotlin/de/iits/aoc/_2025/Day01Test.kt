package de.iits.aoc._2025

import getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day01Test {

    private val sut = Day01()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(1, year = 2025, example = true))
        assertEquals(3, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2(getInputForDay(1, year = 2025, example = true))

        assertEquals(6, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(1, year = 2025, example = false))
        println(result) //1023
    }

    @Test
    fun testInput2() {
        val result = sut.calculate2(getInputForDay(1, year = 2025, example = false))
        println(result) //5899
    }
}