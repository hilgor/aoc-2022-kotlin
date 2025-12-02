package de.iits.aoc._2023

import getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day01Test {

    private val sut = Day01()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(1, year = 2023, example = true))
        assertEquals(142, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2(getInputForDay(1, year = 2023, example = true))

        assertEquals(281, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(1, false, 2023))
        println(result)
    }

    @Test
    fun testInput2() {
        val result = sut.calculate2(getInputForDay(1, false, 2023))
        println(result)
    }
}