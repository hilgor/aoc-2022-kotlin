package de.iits.aoc._2025

import de.iits.aoc.util.filledLines
import kotlin.math.abs

class Day01 {

    fun calculate(inputStr: String): Int =
        inputStr.filledLines().map { extractNumber(it) }
            .let { rotate(it) }

    private fun extractNumber(inputStr: String): Int =
        inputStr.let {
            val sign = if (it.startsWith("L")) -1 else 1
            val number = it
                .removePrefix("L")
                .removePrefix("R")
                .toInt()

            return sign * number
        }

    private fun rotate(instructions: List<Int>): Int {
        var position = 50
        var wasAtZero = 0

        for (i in instructions.indices) {
            var current = position + (instructions[i] % 100)
            when {
                current > 99 -> current = current % 100
                current < 0 -> current = 100 + current
            }
            if (current == 0) wasAtZero++
//            println(current)
            position = current
        }

        return wasAtZero
    }

    private fun rotate2(instructions: List<Int>): Int {
        var position = 50
        var wasAtZero = 0

        for (i in instructions.indices) {
            val inst = instructions[i]
            wasAtZero = wasAtZero + (abs(inst) / 100)

            var current = position + (inst % 100)
            when {
                current > 99 -> {
                    current = current % 100
                    if (position != 0) wasAtZero++
                }

                current < 0 -> {
                    current = 100 + current
                    if (position != 0) wasAtZero++
                }

                current == 0 -> {
                    wasAtZero++
                }
            }

            position = current
        }

        return wasAtZero
    }


    fun calculate2(inputStr: String): Int =
        inputStr.filledLines().map { extractNumber(it) }
            .let { rotate2(it) }


}

