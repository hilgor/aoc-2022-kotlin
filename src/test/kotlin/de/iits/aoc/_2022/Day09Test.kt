package de.iits.aoc._2022

import getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day09Test {

    private val sut = Day09()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(9))
        assertEquals(13, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2(getInputForDay(9))
        assertEquals(1, result)
        val example2 = """
            R 5
            U 8
            L 8
            D 3
            R 17
            D 10
            L 25
            U 20
            """.trimIndent()
        val result2 = sut.calculate2(example2)
        assertEquals(36, result2)
    }

    @Test
    fun testMove() {
        var result = sut.moveTail(1,0, IntArray(2))
        assertEquals(Pair(0,0), result)
        result = sut.moveTail(2,0, result.toList().toIntArray())
        assertEquals(Pair(1,0), result)
        result = sut.moveTail(1,0, result.toList().toIntArray())
        assertEquals(Pair(1,0), result)
        result = sut.moveTail(0,0, result.toList().toIntArray())
        assertEquals(Pair(1,0), result)
        result = sut.moveTail(-1,0, result.toList().toIntArray())
        assertEquals(Pair(0,0), result)

        result = sut.moveTail(-2,0, result.toList().toIntArray())
        assertEquals(Pair(-1,0), result)
        result = sut.moveTail(-2,1, result.toList().toIntArray())
        assertEquals(Pair(-1,0), result)
        result = sut.moveTail(-2,2, result.toList().toIntArray())
        assertEquals(Pair(-2,1), result)
        result = sut.moveTail(-2,1, result.toList().toIntArray())
        assertEquals(Pair(-2,1), result)
        result = sut.moveTail(-2,0, result.toList().toIntArray())
        assertEquals(Pair(-2,1), result)
        result = sut.moveTail(-1,0, result.toList().toIntArray())
        assertEquals(Pair(-2,1), result)
        result = sut.moveTail(-1,-1, result.toList().toIntArray())
        assertEquals(Pair(-1,0), result)

        result = sut.moveTail(2,2, IntArray(2))
        assertEquals(Pair(1,1), result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(9, false))
        println(result)
        assertEquals(6642, result)
    }

    @Test
    fun testInput2() {
        val result = sut.calculate2(getInputForDay(9, false))
        println(result)
        assertEquals(2765, result)
    }
}