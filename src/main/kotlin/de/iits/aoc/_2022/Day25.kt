package de.iits.aoc._2022

import de.iits.aoc.util.filledLines
import de.iits.aoc.util.join

class Day25 {

    fun calculate(inputStr: String): String {
        val number = inputStr.filledLines().sumOf { parseSnafu(it) }

        return convertToSnafu(number)
    }

    fun parseSnafu(input: String): Long {
        var result = 0L

        input.toList().reversed().forEachIndexed { index, char ->
            val a = when (char) {
                '2' -> 2
                '1' -> 1
                '0' -> 0
                '-' -> -1
                '=' -> -2
                else -> throw IllegalArgumentException("unknown SNAFU number")
            }

            var acc = 1L
            repeat(index) { acc *= 5 }
            result += a * acc
        }

        return result
    }

    private tailrec fun convertFromDecimal(rest: Long, base: Int, result: List<String>): List<String> {
        return if (rest < base) {
            result + rest.toString(base)
        } else {
            val remainder = rest % base
            val quotient = rest / base
            convertFromDecimal(quotient, base, result + remainder.toString(base))
        }
    }

    fun convertToSnafu(number: Long): String {
        val base5 = convertFromDecimal(number, 5, emptyList()).joinToString("") { it }
        var overflow = 0
        val result = mutableListOf<Char>()

        base5.map { it.digitToInt() }.forEach {
            val newValue = it + overflow
            overflow = 0
            val value = when (newValue) {
                0 -> '0'
                1 -> '1'
                2 -> '2'
                else -> {
                    overflow = 1
                    when (newValue) {
                        3 -> '='
                        4 -> '-'
                        else -> '0'
                    }
                }
            }
            result.add(value)
        }
        if (overflow == 1)
            result.add('1')
        return result.reversed().join()
    }

    fun calculate2(inputStr: String) =
        inputStr.filledLines()


}

