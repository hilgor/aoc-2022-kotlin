package de.iits.aoc._2022

import de.iits.aoc.util.filledLines
import de.iits.aoc.util.getInputForDay
import de.iits.aoc.util.join
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day10Test {

    private val sut = Day10()

    @Test
    fun testExample() {
        val result = sut.calculate(getInputForDay(10))
        assertEquals(13140, result)
    }

    @Test
    fun testExample2() {
        val result = sut.calculate2(getInputForDay(10))
        val expected = """
            ##..##..##..##..##..##..##..##..##..##..
            ###...###...###...###...###...###...###.
            ####....####....####....####....####....
            #####.....#####.....#####.....#####.....
            ######......######......######......####
            #######.......#######.......#######.....
        """.trimIndent().filledLines().map { it.toList() }

        for (i in result.indices) {
            assertEquals(expected[i].join(), result[i].toList().join())
        }
    }


    ///
    ///



    @Test
    fun testInput() {
        val result = sut.calculate(getInputForDay(10, false))
        println(result)
        assertEquals(12840, result)
    }

    @Test
    fun testInput2() {
        val result = sut.calculate2(getInputForDay(10, false))
        println(result)
        val expected = """
            ####.#..#...##.####.###....##.####.####.
            ...#.#.#.....#.#....#..#....#.#.......#.
            ..#..##......#.###..###.....#.###....#..
            .#...#.#.....#.#....#..#....#.#.....#...
            #....#.#..#..#.#....#..#.#..#.#....#....
            ####.#..#..##..#....###...##..#....####.
        """.trimIndent().filledLines().map { it.toList() }

        for (i in result.indices) {
            assertEquals(expected[i].join(), result[i].toList().join())
        }
    }
}