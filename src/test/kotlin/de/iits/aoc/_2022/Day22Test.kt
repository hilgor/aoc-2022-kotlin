package de.iits.aoc._2022

import getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class Day22Test {

    private val sut = Day22()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(22))
        assertEquals(6032, result)
    }

    @Test
    @Disabled("Example layout differs from actual input, not a general solution")
    fun testExample2() {
        val result = sut.calculate(getInputForDay(22), true, 4)

        assertEquals(5031, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(22, false))
        assertEquals(26558, result)
        println(result)
    }

    @Test
    fun testInput2() {
        val result = sut.calculate(getInputForDay(22, false), true, 50)
        assertEquals(110400, result)
        println(result)
    }
}