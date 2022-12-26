package de.iits.aoc._2022

import filledLines
import getAllInts

class Day07 {

    private val dirSizes = mutableMapOf<String, Long>()

    fun calculate(inputStr: String): Long {
        processDirs(inputStr.filledLines()
            .filterNot { it.startsWith("dir") || it.startsWith("${'$'} ls") })

        return dirSizes.filter { it.value <= 100000 }.values.sum()
    }


    fun calculate2(inputStr: String) : Long {
        processDirs(inputStr.filledLines()
            .filterNot { it.startsWith("dir") || it.startsWith("${'$'} ls") })

        val neededSpace = 30000000 - (70000000 - dirSizes["|/"]!!)
        println("Used space: ${dirSizes["|/"]!!}, needed space: $neededSpace")

        return dirSizes.filter { it.value >= neededSpace }
            .toList().minByOrNull { (_, value) -> value }!!.second
    }


    private fun processDirs(input: List<String>) {
        val dirTree = ArrayDeque<String>()

        for (line in input) {
            when {
                line.startsWith("${'$'} cd ..") -> {
                    val currSize = dirSizes[dirTree.first()]
                    dirTree.removeFirst()
                    updateSize(dirTree, currSize!!)
                }
                line.startsWith("${'$'} cd") -> dirTree.addFirst(getName(dirTree, line))
                else -> updateSize(dirTree, line.getAllInts().first().toLong())
            }
        }

        while (dirTree.isNotEmpty()) {
            val currSize = dirSizes[dirTree.first()]
            dirTree.removeFirst()
            updateSize(dirTree, currSize!!)
        }
    }

    private fun updateSize(dirTree: ArrayDeque<String>, size: Long) {
        if (dirTree.isNotEmpty()) {
            dirSizes[dirTree.first()] = dirSizes.getOrPut(dirTree.first()) { 0 } + size
        }
    }

    private fun getName(dirTree: ArrayDeque<String>, line: String) =
        dirTree.joinToString("|") { it } + "|" + line.split(" ").last()


}

