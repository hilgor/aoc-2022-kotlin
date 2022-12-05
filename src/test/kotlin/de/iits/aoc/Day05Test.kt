package de.iits.aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day05Test {

    private val sut = Day05()

    @Test
    fun testExample() {
        val result = sut.calculate(EXAMPLE_INPUT, EXAMPLE_INPUT2, false)
        assertEquals("CMZ", result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate(EXAMPLE_INPUT, EXAMPLE_INPUT2, true)
        assertEquals("MCD", result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(INPUT, INPUT2, false)
        println(result)
        assertEquals("BZLVHBWQF", result)
    }

    @Test
    fun testInput2() {
        val result = sut.calculate(INPUT, INPUT2, true)
        println(result)
        assertEquals("TDGJQTZSL", result)
    }
}