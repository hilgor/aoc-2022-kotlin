package de.iits.aoc._2022

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day_Template_Test {

    private val sut = Day04()

    @Test
    fun testExample() {
        val result = sut.calculate(EXAMPLE_INPUT)
        assertEquals(2, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2(EXAMPLE_INPUT)

        assertEquals(4, result)
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