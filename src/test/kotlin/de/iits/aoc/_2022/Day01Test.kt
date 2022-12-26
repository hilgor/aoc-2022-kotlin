package de.iits.aoc._2022

import getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day01Test {

    private val sut = Day01()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(1), 1)

        assertEquals(24000, result)
    }

    @Test
    fun testExampleLast3() {
        val result = sut.calculate(getInputForDay(1), 3)

        assertEquals(45000, result)
    }

    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(1, false), 1)
        println(result)
        assertEquals(70374, result)
    }

    @Test
    fun testInputLast3() {
        val result = sut.calculate(getInputForDay(1, false), 3)
        println(result)
        assertEquals(204610, result)
    }
}