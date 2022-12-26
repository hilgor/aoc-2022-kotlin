package de.iits.aoc._2022

import getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class Day18Test {

    private val example = Day18(getInputForDay(18))
    private val real = Day18(getInputForDay(18, false))

    @Test
    fun testExample() {
        val result = example.calculate()
        assertEquals(64, result)
    }

    @Test
    fun testExample2() {
        val result = example.calculate2()
        assertEquals(58, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = real.calculate()
        println(result)
        assertEquals(3346, result)
    }

    @Test
    @Disabled("Takes about 1 min")
    fun testInput2() {
        val result = real.calculate2()
        println(result)
        assertEquals(1980, result)
    }

    @Test
    fun countSides() {
        val point1 = Point3D(1, 1, 1)
        val point2 = Point3D(2, 1, 1)
        val points = listOf(point1, point2)
        assertEquals(10, points.sumOf { example.countSides(it, points) })
    }
}