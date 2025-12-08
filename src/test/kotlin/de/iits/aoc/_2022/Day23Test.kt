package de.iits.aoc._2022

import de.iits.aoc.util.getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day23Test {

    private val sut = Day23(getInputForDay(23))

    @Test
    fun testExample() {
        val result = sut.calculate()
        assertEquals(110, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2()

        assertEquals(20, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = Day23(getInputForDay(23, false)).calculate()
        assertEquals(3990, result)
        println(result)
    }

    @Test
    fun testInput2() {
        val result = Day23(getInputForDay(23, false)).calculate2()
        assertEquals(1057, result)
        println(result)
    }
}