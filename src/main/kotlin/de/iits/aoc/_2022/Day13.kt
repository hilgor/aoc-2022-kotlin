package de.iits.aoc._2022

import filledLines
import kotlin.math.max

class Day13 {

    fun calculate(inputStr: String): Int {
        val pairs = inputStr.trim().split("\n\n")
            .map { it.lines() }
            .map { Pair(convert(it.first()), convert(it.last())) }

        return pairs.filter { compare(it.first, it.second) < 0 }.sumOf { pairs.indexOf(it) + 1 }
    }


    fun convert(input: String): List<Any> {

        val lists = mutableListOf<Any>()
        var currentList = lists
        val parentMap = mutableMapOf<Int, MutableList<Any>>()
        var count = 0
        var number = ""

        for (current in input.toList()) {
            if (current == '[') {
                count++
                val newCurrentList = mutableListOf<Any>(count)
                parentMap[count] = currentList
                currentList = newCurrentList
                continue
            }
            if (listOf(',', ']').none { it == current }) {
                number = "$number$current"
            } else if (number.isNotBlank()) {
                currentList.add(number.toInt())
                number = ""
            }
            if (current == ']') {
                parentMap[currentList[0]]?.let {
                    it.add(currentList)
                    currentList = it
                }
            }
        }
        cleanup(lists.first() as MutableList<Any>)
        return lists.first() as MutableList<Any>
    }

    private fun cleanup(list: MutableList<Any>) {
        list.removeFirst()
        list.forEach {
            if (it is List<*>) cleanup(it as MutableList<Any>)
        }
    }

    fun compare(left: List<Any>, right: List<Any>): Int {
        val leftItem = left.toMutableList()
        val rightItem = right.toMutableList()

        if (leftItem.isEmpty() && rightItem.isEmpty()) return 0

        for (i in 0 until max(left.size, right.size)) {
            if (leftItem.isEmpty()) return -1
            else if (rightItem.isEmpty()) return 1

            val compare = compareItems(leftItem.first(), rightItem.first())
            when {
                compare < 0 -> return -1
                compare > 0 -> return 1
                else -> {
                    leftItem.removeFirst()
                    rightItem.removeFirst()
                }
            }

        }
        return 0
    }

    private fun compareItems(left: Any, right: Any): Int {
        return if (left is Int && right is Int) {
            left.compareTo(right)
        } else compare(convertToList(left), convertToList(right))
    }

    private fun convertToList(item: Any) =
        if (item is List<*>) item as List<Any>
        else listOf(item)


    fun calculate2(inputStr: String): Int {
        val divider = """
            [[2]]
            [[6]]
        """.trimIndent()
        val packets = (inputStr + "\n" + divider).filledLines()
            .filter { it.isNotBlank() }
            .map { convert(it) }
            .sortedWith(this::compare)

        return packets
            .filter {
                listOf<Any>(listOf<Any>(2)) == it
                        || listOf<Any>(listOf<Any>(6)) == it
            }
            .map { packets.indexOf(it) + 1 }
            .reduce { a, b -> a.times(b) }
    }

}


