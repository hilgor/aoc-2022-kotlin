package de.iits.aoc._2022

import de.iits.aoc.util.getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class Day24Test {

    private val sut = Day24()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(24))
        assertEquals(18, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate(getInputForDay(24), true)

        assertEquals(54, result)
    }


    ///
    ///



    @Test
    @Disabled("runs about 3 minutes")
    fun testInput() {
        val result = sut.calculate(getInputForDay(24, false))
        println(result)
        assertEquals(334, result)
    }

    @Test
    @Disabled("runs about 8 minutes")
    fun testInput2() {
        val result = sut.calculate(getInputForDay(24, false), true)
        println(result)
        assertEquals(934, result)
    }
}