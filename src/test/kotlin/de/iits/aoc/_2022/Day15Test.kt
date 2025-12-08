package de.iits.aoc._2022

import de.iits.aoc.util.getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

internal class Day15Test {

    private val sut = Day15()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(15), 10)
        assertEquals(26, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2(getInputForDay(15), 20)

        assertEquals(56000011, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(15, false), 2000000)
        println(result)
        assertEquals(4737567, result)
    }

    @Test
    fun testInput2() {
        val result = sut.calculate2(getInputForDay(15, false), 4000000)
        println(result)
        assertEquals(13267474686239, result)
    }


    @Test
    fun computeCoveredInRow() {
        val sensor = Point(8,7)
        val beacon = Point(2,10)

        var result = sut.computeCoveredInRow(sensor, beacon, -2).sorted()
        assertEquals(8, result.first())
        assertEquals(8, result.last())

        result = sut.computeCoveredInRow(sensor, beacon, -1).sorted()
        assertEquals(7, result.first())
        assertEquals(9, result.last())

        result = sut.computeCoveredInRow(sensor, beacon, 0).sorted()
        assertEquals(6, result.first())
        assertEquals(10, result.last())

        result = sut.computeCoveredInRow(sensor, beacon, 10).sorted()
        assertEquals(3, result.first())
        assertEquals(14, result.last())
    }

    @Test
    fun computeUnCoveredInRow() {
        val sensor = Point(8,7, distance = 9)

        var result = sut.computeUncoveredInRow(sensor, 0, 0, 20).sorted()
        assertEquals(16, result.size)
        assertFalse(result.contains(6))
        assertFalse(result.contains(10))

        result = sut.computeUncoveredInRow(sensor, 10, 0, 20).sorted()
        assertEquals(8, result.size)
        assertFalse(result.contains(3))
        assertFalse(result.contains(14))

    }
}