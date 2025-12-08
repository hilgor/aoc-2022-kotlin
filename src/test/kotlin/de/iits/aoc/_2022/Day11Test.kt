package de.iits.aoc._2022

import de.iits.aoc.util.getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day11Test {

    private val sut = Day11()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(11), 20, 3, 23 * 19 * 13 * 17)
        assertEquals(10605, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate(getInputForDay(11), 10000, 1,23 * 19 * 13 * 17)
        assertEquals(2713310158, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(11, false), 20, 3,
            19* 5* 11* 17* 7* 13* 3* 2)
        println(result)
        assertEquals(101436, result)
    }

    @Test
    fun testInput2() {
        val result = sut.calculate(getInputForDay(11, false), 10000, 1,
            19* 5* 11* 17* 7* 13* 3* 2)
        println(result)
        assertEquals(19754471646, result)
    }
}