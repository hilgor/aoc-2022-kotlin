package de.iits.aoc._2022

import de.iits.aoc.util.getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day20Test {

    private val sut = Day20(getInputForDay(20))

    @Test
    fun testExample() {
        val result = sut.calculate()
        assertEquals(3, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate(811589153, 10)

        assertEquals(1623178306, result)
    }


    ///
    ///


    @Test
    fun testInput() {
        val result = Day20(getInputForDay(20, false)).calculate()
        println(result)
        assertEquals(2203, result)
    }

    @Test
    fun testInput2() {
        val result = Day20(getInputForDay(20, false)).calculate(811589153, 10)
        println(result)
        assertEquals(6641234038999, result)
    }


    @Test
    fun putAtValue() {
        val sut = Day20(getInputForDay(20))
        val list = listOf<Pair<Int, Long>>(1 to 1, 2 to 2, 3 to -3, 4 to 3, 5 to -2, 6 to 0, 7 to 4)
        val toTest = list.toMutableList()


        sut.putAtValue(1 to 1, toTest)
        assertEquals(listOf<Long>(2, 1, -3, 3, -2, 0, 4), toTest.map { it.second })

        sut.putAtValue(2 to 2, toTest)
        assertEquals(listOf<Long>(1, -3, 2, 3, -2, 0, 4), toTest.map { it.second })

        sut.putAtValue(3 to -3, toTest)
        assertEquals(listOf<Long>(1, 2, 3, -2, -3, 0, 4), toTest.map { it.second })

        sut.putAtValue(4 to 3, toTest)
        assertEquals(listOf<Long>(1, 2, -2, -3, 0, 3, 4), toTest.map { it.second })

        sut.putAtValue(5 to -2, toTest)
        assertEquals(listOf<Long>(1, 2, -3, 0, 3, 4, -2), toTest.map { it.second })

        sut.putAtValue(6 to 0, toTest)
        assertEquals(listOf<Long>(1, 2, -3, 0, 3, 4, -2), toTest.map { it.second })

        sut.putAtValue(7 to 4, toTest)
        assertEquals(listOf<Long>(1, 2, -3, 4, 0, 3, -2), toTest.map { it.second })


    }
}