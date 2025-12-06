package de.iits.aoc._2025

import filledLines

class Day05 {

    fun calculate(inputStr: String): Int {
        val db = inputStr.split("\n\n")

        val freshIds = db[0].lines()
            .map { it.split("-") }
            .map { it[0].toLong() to it[1].toLong() }
            .toSet()

        val toCheckIds = db[1].lines()
            .map { it.toLong() }
            .toSet()

        return toCheckIds.count {
            freshIds.any { (from, to) ->
                it in from..to
            }
        }
    }


    fun calculate2(inputStr: String) =
        inputStr.split("\n\n")
            .let {
                val ranges = it[0].lines()
                    .map { it.split("-") }
                    .map { it[0].toLong() to it[1].toLong() }
                    .sortedBy { it.first }
                    .toSet()

                val mergedRanges = ranges.drop(1)
                    .fold(mutableListOf(ranges.first())) { resultList, range ->
                        val last = resultList.last()
                        if (range.first > last.second + 1) {
                            // no overlap
                            resultList += range
                        } else {
                            resultList[resultList.lastIndex] = last.first to maxOf(last.second, range.second)
                        }
                        resultList
                    }

                mergedRanges.sumOf { it.second - it.first + 1 }
            }


}

