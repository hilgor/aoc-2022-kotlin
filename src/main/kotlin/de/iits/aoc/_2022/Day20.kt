package de.iits.aoc._2022

import filledLines

class Day20(inputStr: String) {

    private val inputList = parseInput(inputStr)

    fun calculate(multiplier: Long = 1, times: Int = 1): Long {
        val input = inputList.map { it.first to it.second.toLong() * multiplier }
        val modList = input.toMutableList()

        repeat(times) {
            for (element in input) {
                putAtValue(element, modList)
            }
        }

        val position = modList.indexOf(modList.first { it.second == 0L })
        println(
            "1000: ${modList.nth(position + 1000).second}, " +
                    "2000: ${modList.nth(position + 2000).second}, " +
                    "3000: ${modList.nth(position + 3000).second}, "
        )
        return modList.nth(position + 1000).second
            .plus(modList.nth(position + 2000).second)
            .plus(modList.nth(position + 3000).second)
    }

    private fun parseInput(inputStr: String): List<Pair<Int, Int>> {
        var counter = 1
        return inputStr.filledLines()
            .map { counter++ to it.toInt() }
    }


    fun putAtValue(element: Pair<Int, Long>, list: MutableList<Pair<Int, Long>>) {

        var position = list.indexOf(element) + element.second
        position %= (list.size - 1)
        if (position <= 0) position += (list.size - 1)

        list.remove(element)
        list.add(position.toInt(), element)
    }


    fun calculate2() =
        inputList.map { it.first to it.second.toLong() * 811589153L }


}


fun <T> List<T>.nth(n: Int): T = this[n % size]

