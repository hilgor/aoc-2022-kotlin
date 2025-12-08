package de.iits.aoc._2025

import de.iits.aoc.util.getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day06Test {

    private val sut = Day06()
    private val day = 6

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(day, example = true, 2025))
        assertEquals(4277556, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2(getInputForDay(day, example = true, 2025))

        assertEquals(3263827, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(day, false, 2025))
        println(result) //4580995422905
    }

    @Test
    fun testInput2() {
        val result = sut.calculate2(getInputForDay(day, false, 2025))
        println(result) //10875057285868
    }
}