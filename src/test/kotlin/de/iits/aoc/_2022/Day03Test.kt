package de.iits.aoc._2022

import getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day03Test {

    private val sut = Day03()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(3))

        assertEquals(157, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2(getInputForDay(3))

        assertEquals(70, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(3, false))
        println(result)
        assertEquals(7716, result)
    }

    @Test
    fun testInput2() {
        val result = sut.calculate2(getInputForDay(3, false))
        println(result)
        assertEquals(2973, result)
    }
}