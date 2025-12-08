package de.iits.aoc._2022

import de.iits.aoc.util.join

class Day06 {

    fun calculate(inputStr: String, differentSize: Int = 4): Int {
        val match = inputStr.trim().toList().windowed(differentSize)
            .find { isDifferent(it, differentSize) }!!.join()

        return inputStr.indexOf(match) + differentSize
    }

    private fun isDifferent(input: List<Char>, differentSize: Int) =
        input.distinct().size == differentSize

}

