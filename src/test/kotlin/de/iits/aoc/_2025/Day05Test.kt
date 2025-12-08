package de.iits.aoc._2025

import de.iits.aoc.util.getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day05Test {

    private val sut = Day05()
    private val day = 5

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(day, example = true, 2025))
        assertEquals(3, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2(getInputForDay(day, example = true, 2025))

        assertEquals(14, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(day, false, 2025))
        println(result) //761
    }

    @Test
    fun testInput2() {
        val result = sut.calculate2(getInputForDay(day, false, 2025))
        println(result) //345755049374932
    }
}