package de.iits.aoc._2025

import de.iits.aoc.util.getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day03Test {

    private val sut = Day03()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(3, example = true, 2025))
        assertEquals(357, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2(getInputForDay(3, example = true, 2025))

        assertEquals(3121910778619L, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(3, false, 2025))
        println(result) //17092
    }

    @Test
    fun testInput2() {
        val result = sut.calculate2(getInputForDay(3, false, 2025))
        println(result) //170147128753455
    }
}