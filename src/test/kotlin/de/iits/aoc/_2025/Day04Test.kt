package de.iits.aoc._2025

import de.iits.aoc.util.getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day04Test {

    private val sut = Day04()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(4, example = true, 2025))
        assertEquals(13, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2(getInputForDay(4, example = true, 2025))

        assertEquals(43, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(4, false, 2025))
        println(result) //1547
    }

    @Test
    fun testInput2() {
        val result = sut.calculate2(getInputForDay(4, false, 2025))
        println(result) //8948
    }
}