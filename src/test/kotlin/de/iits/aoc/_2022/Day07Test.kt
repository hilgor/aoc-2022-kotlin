package de.iits.aoc._2022

import de.iits.aoc.util.getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day07Test {

    private val sut = Day07()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(7))
        assertEquals(95437, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2(getInputForDay(7))

        assertEquals(24933642, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(7, false))
        println(result)
        assertEquals(1390824, result)
    }

    @Test
    fun testInput2() {
        val result = sut.calculate2(getInputForDay(7, false))
        println(result)
        assertEquals(7490863, result)
    }
}