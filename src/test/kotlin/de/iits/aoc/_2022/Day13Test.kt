package de.iits.aoc._2022

import de.iits.aoc.util.getInputForDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class Day13Test {

    private val sut = Day13()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(13))
        assertEquals(13, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2(getInputForDay(13))

        assertEquals(140, result)
    }


    ///
    ///


    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(13, false))
        println(result)
        assertEquals(5580, result)
    }

    @Test
    fun testInput2() {
        val result = sut.calculate2(getInputForDay(13, false))
        println(result)
        assertEquals(26200, result)
    }

    @Test
    fun convert() {
        assertEquals(listOf<Any>(1, 1, 3, 1, 1), sut.convert("[1,1,3,1,1]"))
        assertEquals(listOf(listOf<Any>(1), listOf<Any>(2, 3, 4)), sut.convert("[[1],[2,3,4]]"))
        assertEquals(listOf(listOf<Any>(listOf<Any>())), sut.convert("[[[]]]"))
        assertEquals(
            listOf(1, listOf(2, listOf(3, listOf(4, listOf<Any>(5, 6, 7)))), 8, 9),
            sut.convert("[1,[2,[3,[4,[5,6,7]]]],8,9]")
        )
        assertEquals(
            listOf(listOf(),listOf(10,1,listOf(7,8,listOf<Any>(10,7,0,8)),listOf<Any>(),listOf(0, listOf<Any>(1,5,7,6),8,listOf<Any>(5))),listOf<Any>(10)),
            sut.convert("[[],[10,1,[7,8,[10,7,0,8]],[],[0,[1,5,7,6],8,[5]]],[10]]")
        )
        assertEquals(
            listOf(listOf(), listOf(), listOf<Any>(listOf(listOf<Any>(),9,listOf<Any>(),9,listOf<Any>(2)),listOf<Any>(listOf<Any>(10,8,1,6),listOf<Any>(7,8),listOf<Any>(8,2,5,6,9),listOf<Any>()),listOf<Any>(),listOf<Any>(9,6,2),listOf<Any>(listOf<Any>(0,4,7),listOf<Any>(),3,8,listOf<Any>(10,1,0,6,4)))),
            sut.convert("[[],[],[[[],9,[],9,[2]],[[10,8,1,6],[7,8],[8,2,5,6,9],[]],[],[9,6,2],[[0,4,7],[],3,8,[10,1,0,6,4]]]]")
        )
    }

    @Test
    fun compare() {
        assertTrue(0 < sut.compare(
            sut.convert("[[],[10,1,[7,8,[10,7,0,8]],[],[0,[1,5,7,6],8,[5]]],[10]]"),
            sut.convert("[[],[],[[[],9,[],9,[2]],[[10,8,1,6],[7,8],[8,2,5,6,9],[]],[],[9,6,2],[[0,4,7],[],3,8,[10,1,0,6,4]]]]")))
    }
}