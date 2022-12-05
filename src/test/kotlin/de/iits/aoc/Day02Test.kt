package de.iits.aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day02Test {

    private val sut = Day02()

    @Test
    fun testExample() {
        val result = sut.calculate(EXAMPLE_INPUT)

        assertEquals(15, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2(EXAMPLE_INPUT)

        assertEquals(12, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(INPUT)
        println(result)
    }

    @Test
    fun testInput2() {
        val result = sut.calculate2(INPUT)
        println(result)
    }
}