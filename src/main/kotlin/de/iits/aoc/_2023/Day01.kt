package de.iits.aoc._2023

import filledLines
import splitToInts

class Day01 {

    fun calculate(inputStr: String): Int =
        inputStr.filledLines().sumOf { extractNumbers(it) }

    private fun extractNumbers(inputStr: String): Int =
        inputStr.splitToInts()
            .let {
                it.first() * 10 + it.last()
            }

    private fun extractNumbers2(inputStr: String): Int =
        inputStr
            .let {
                val chars = it.toCharArray()

//                val numbers = mutableListOf<Int>()
//
//                //iterate over windowed(5) and check if it contains a number
//                it.windowed(5).forEach { window ->
//
//                    if (window.contains("one")) {
//                        numbers.add(1)
//                    }
//                    if (window.contains("two")) {
//                        numbers.add(2)
//                    }
//                    if (window.contains("three")) {
//                        numbers.add(3)
//                    }
//                    if (window.contains("four")) {
//                        numbers.add(4)
//                    }
//                    if (window.contains("five")) {
//                        numbers.add(5)
//                    }
//                    if (window.contains("six")) {
//                        numbers.add(6)
//                    }
//                    if (window.contains("seven")) {
//                        numbers.add(7)
//                    }
//                    if (window.contains("eight")) {
//                        numbers.add(8)
//                    }
//                    if (window.contains("nine")) {
//                        numbers.add(9)
//                    }
//                    if (window.last().isDigit()) {
//                        numbers.add(window.last().digitToInt())
//                    }
//                }

                var current = ""
                for (i in chars.indices) {
//                    if (chars[i].isDigit()) {
//                        numbers.add(chars[i].digitToInt())
//                        continue
//                    }

                    current += chars[i]
                    current = current
                        .replace("nine", "n9e")
                        .replace("five", "f5e")
                        .replace("eight", "e8t")
                        .replace("seven", "s7n")
                        .replace("six", "s6x")
                        .replace("four", "4")
                        .replace("three", "t3e")
                        .replace("two", "t2o")
                        .replace("one", "o1e")
                }
                current
            }
            .splitToInts()
            .let {
//                println(it)
                it.first() * 10 + it.last()
            }


    fun calculate2(inputStr: String): Int =
        inputStr.filledLines().sumOf { extractNumbers2(it) }


}

