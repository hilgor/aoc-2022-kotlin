package de.iits.aoc._2022

import de.iits.aoc.util.getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day05Test {

    private val sut = Day05()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(5), false)
        assertEquals("CMZ", result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate(getInputForDay(5), true)
        assertEquals("MCD", result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(5, false), false)
        println(result)
        assertEquals("BZLVHBWQF", result)
    }

    @Test
    fun testInput2() {
        val result = sut.calculate(getInputForDay(5, false), true)
        println(result)
        assertEquals("TDGJQTZSL", result)
    }
}