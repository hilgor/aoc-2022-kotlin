package de.iits.aoc._2022

import de.iits.aoc.util.getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class Day16Test {

    private val sut = Day16()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(16))
        assertEquals(1651, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2(getInputForDay(16))

        assertEquals(1707, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(16, false))
        println(result)
        assertEquals(1871, result)
    }

    @Test
    @Disabled("about 16 mins on my machine")
    fun testInput2() {
        val result = sut.calculate2(getInputForDay(16, false))
        println(result)
        assertEquals(2416, result)
    }
}