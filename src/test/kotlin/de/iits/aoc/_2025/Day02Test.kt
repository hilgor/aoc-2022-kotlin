package de.iits.aoc._2025

import de.iits.aoc.util.getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day02Test {

    private val sut = Day02()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(2, example = true, 2025))
        assertEquals(1227775554, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2(getInputForDay(2, example = true, 2025))

        assertEquals(4174379265, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(2, false, 2025))
        println(result) //18893502033
    }

    @Test
    fun testInput2() {
        val result = sut.calculate2(getInputForDay(2, false, 2025))
        println(result) //26202168557
    }
}