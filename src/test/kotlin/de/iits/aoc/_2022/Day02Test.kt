package de.iits.aoc._2022

import getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day02Test {

    private val sut = Day02()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(2))

        assertEquals(15, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2(getInputForDay(2))

        assertEquals(12, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(2, false))
        assertEquals(13446, result)
        println(result)
    }

    @Test
    fun testInput2() {
        val result = sut.calculate2(getInputForDay(2, false))
        assertEquals(13509, result)
        println(result)
    }
}