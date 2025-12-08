package de.iits.aoc._2022

import de.iits.aoc.util.getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day17Test {

    private val sut = Day17()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(17))
        assertEquals(3068, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2(getInputForDay(17))

        assertEquals(1514285714288L, result)
    }


    ///
    ///


    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(17, false))
        println(result)
        assertEquals(3055, result)
    }

    @Test
    fun testInput2() {
        val result = sut.calculate2(getInputForDay(17, false))
        println(result)
        assertEquals(1507692307690, result)
    }
}