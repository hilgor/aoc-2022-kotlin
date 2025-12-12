package de.iits.aoc._2025

import de.iits.aoc.util.getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day11Test {

    private val sut = Day11()
    private val day = 11

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(day, example = true, 2025))
        assertEquals(5, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2(getInputForDay(day, example = true, 2025))

        assertEquals(2, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(day, false, 2025))
        println(result) //652
    }

    @Test
    fun testInput2() {
        val result = sut.calculate2(getInputForDay(day, false, 2025))
        println(result)
        //362956369749210
    }
}