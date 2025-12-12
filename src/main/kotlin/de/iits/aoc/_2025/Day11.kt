package de.iits.aoc._2025

import de.iits.aoc.util.filledLines
import kotlin.collections.HashMap

class Day11 {

    fun calculate(inputStr: String): Int {
        val deviceMap = inputStr.filledLines()
            .associate { it.substring(0, 3) to it.substring(5).split(" ") }

        val cache = mutableMapOf<String, Int>()

        return countPaths(deviceMap, "you", cache)
    }

    private fun countPaths(deviceMap: Map<String, List<String>>, current: String, cache: MutableMap<String, Int>): Int =
        deviceMap[current]!!.let { targets ->
            when {
                cache.containsKey(current) -> cache[current]!!
                targets[0] == "out" -> 1

                else -> {
                    val count = targets.sumOf { target ->
                        countPaths(deviceMap, target, cache)
                    }
                    cache[current] = count
                    count
                }
            }
        }


    fun calculate2(inputStr: String): Long {
        val deviceMap = inputStr.filledLines()
            .associate { it.substring(0, 3) to it.substring(5).split(" ") }

        val cache = HashMap<Triple<String, Boolean, Boolean>, Long>()

        return countPaths2(deviceMap, "svr", false, false, cache)
    }

    private fun countPaths2(
        deviceMap: Map<String, List<String>>, current: String,
        seenDac: Boolean, seenFft: Boolean,
        cache: HashMap<Triple<String, Boolean, Boolean>, Long>,
    ): Long =
        deviceMap[current]!!.let { targets ->
//            println("At $current, seenDac=$seenDac, seenFft=$seenFft")
            when {
                cache.containsKey(Triple(current, seenDac, seenFft)) -> cache[Triple(current, seenDac, seenFft)]!!
                targets[0] == "out" && seenDac && seenFft -> 1
                targets[0] == "out" && !(seenDac && seenFft) -> 0

                else -> {
                    val count = targets.sumOf { target ->
                        val isDac = target == "dac" || seenDac
                        val isFft = target == "fft" || seenFft
                        countPaths2(deviceMap, target, isDac, isFft, cache)
                    }
                    cache[Triple(current, seenDac, seenFft)] = count
                    count
                }
            }
        }
}

