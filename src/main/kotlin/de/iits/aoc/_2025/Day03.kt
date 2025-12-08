package de.iits.aoc._2025

import de.iits.aoc.util.filledLines
import de.iits.aoc.util.splitToInts

class Day03 {

    fun calculate(inputStr: String) = inputStr.filledLines()
        .map { it.splitToInts() }
        .map { it.map { it.toLong() } }
        .sumOf { findMaxJoltage(it, 2) }

    private fun findMaxJoltage(batteries: List<Long>, digits: Int): Long {

        val digitValues = MutableList(digits) { 0L }
        var lastDigitIndex = -1

        for (i in 0 until digits) {
            val remainingDigits = digits - i - 1
            val endIndex = batteries.size - remainingDigits

            val (index, value) = findMaxDigit(batteries, lastDigitIndex + 1, endIndex)

            digitValues.indices.take(i).forEach { digitValues[it] *= 10L }

            lastDigitIndex = index
            digitValues[i] = value
        }

        return digitValues.sum()
//            .also { println(it) }
    }

    private fun findMaxDigit(batteries: List<Long>, startIndex: Int = 0, endIndex: Int): Pair<Int, Long> =
        batteries.withIndex()
            .filter { it.index in startIndex until endIndex }
            .asSequence()
            .map { it.index to it.value }
            .maxBy { it.second }


    fun calculate2(inputStr: String) =
        inputStr.filledLines()
            .map { it.splitToInts() }
            .map { it.map { it.toLong() } }
            .sumOf { findMaxJoltage(it, 12) }


}

