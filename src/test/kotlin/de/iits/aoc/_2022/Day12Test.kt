package de.iits.aoc._2022

import de.iits.aoc.util.getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day12Test {

    private val sut = Day12()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(12), "S")
        assertEquals(31, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate(getInputForDay(12), "aS")

        assertEquals(29, result)
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(12, false), "S")
        println(result)
        assertEquals(350, result)
    }

    @Test
    fun testInput2() {
        val result = sut.calculate(getInputForDay(12, false), "aS")
        println(result)
        assertEquals(349, result)
    }


    @Test
    fun canStepTo(){
        val a = Point(0,0, 'a')
        val b = Point(0,0, 'b')
        val d = Point(0,0, 'd')
        val z = Point(0,0, 'z')
        val y = Point(0,0, 'y')
        val e = Point(0,0, 'E')
        val s = Point(0,0, 'S')
        assertEquals(true, a.canStepTo(a))
        assertEquals(true, a.canStepTo(b))
        assertEquals(false, a.canStepTo(d))
        assertEquals(false, a.canStepTo(z))
        assertEquals(true, y.canStepTo(e))
        assertEquals(true, z.canStepTo(e))
        assertEquals(true, z.canStepTo(y))
        assertEquals(true, z.canStepTo(a))
        assertEquals(false, d.canStepTo(e))
        assertEquals(true, d.canStepTo(a))
        assertEquals(true, s.canStepTo(a))
    }
}