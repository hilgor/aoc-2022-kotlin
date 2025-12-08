package de.iits.aoc._2022

import de.iits.aoc.util.getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import kotlin.test.assertNull

internal class Day14Test {

    private val sut = Day14()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(14), null)
        assertEquals(24, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate(getInputForDay(14), Point(500,0))

        assertEquals(93, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(14, false), null)
        println(result)
        assertEquals(1003, result)
    }

    @Test
    @Disabled("Runs about 4 mins on my machine")
    fun testInput2() {
        val result = sut.calculate(getInputForDay(14, false), Point(500,0))
        println(result)
        assertEquals(25771, result)
    }

    @Test
    fun fallDown() {
        val start = Point(500, 0)

        val left = Point(499, 5)
        val center = Point(500, 5)
        val right = Point(501, 5)
        assertNull(sut.fallDown(start, listOf()))
        assertNull(sut.fallDown(start, listOf(center)))
        assertNull(sut.fallDown(start, listOf(left, center)))
        assertNull(sut.fallDown(start, listOf(center, right)))
        assertEquals(Point(500, 4, '●'), sut.fallDown(start, listOf(left, right, center)))
    }

    @Test
    fun unicode() {
        println("\u2588")
        println("♠")
        println("█")
        println("раз два три")
    }
}