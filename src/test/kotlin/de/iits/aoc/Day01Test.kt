package de.iits.aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day01Test {

    private val sut = Day01()

    @Test
    fun testExample() {
        val result = sut.calculate(EXAMPLE_INPUT, 1)

        assertEquals(24000, result)
    }

    @Test
    fun testExampleLast3() {
        val result = sut.calculate(EXAMPLE_INPUT, 3)

        assertEquals(45000, result)
    }

    @Test
    fun testInput() {
        val result = sut.calculate(INPUT, 1)
        println(result)
        assertEquals(70374, result)
    }

    @Test
    fun testInputLast3() {
        val result = sut.calculate(INPUT, 3)
        println(result)
//        assertEquals(70374, result)
    }
}