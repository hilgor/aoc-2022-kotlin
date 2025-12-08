package de.iits.aoc._2022

import de.iits.aoc.util.filledLines
import de.iits.aoc.util.rotate90
import de.iits.aoc.util.splitToInts

class Day08 {

    var maxX = 0
    var maxY = 0

    fun calculate(inputStr: String) =
        countVisible(inputStr.filledLines()
            .map { it.splitToInts() })


    fun calculate2(inputStr: String) =
        findMaxScore(inputStr.filledLines()
            .map { it.splitToInts() })


    private fun countVisible(input: List<List<Int>>): Int {
        maxX = input[0].size
        maxY = input.size

        val visibleMap = List(maxY) { MutableList(maxX) { false } }

        setVisible(input, visibleMap)

        val rotatedIntMap = rotate90(input, 0)
        val rotatedBolMap = rotate90(visibleMap, false)

        maxX = rotatedIntMap[0].size
        maxY = rotatedIntMap.size

        setVisible(rotatedIntMap, rotatedBolMap)

        return rotatedBolMap.sumOf { it.count { bol -> bol } }

    }

    private fun setVisible(input: List<List<Int>>, visibleMap: List<MutableList<Boolean>>) {
        for (i in 0 until maxY) {
            for (j in 0 until maxX) {
                if (!visibleMap[j][i])
                    visibleMap[j][i] = isVisible(input[j][i], input[j], i, j)
            }
        }
    }

    private fun isVisible(first: Int, others: List<Int>, x: Int, y: Int): Boolean {
        if (x == 0 || x == maxX - 1 || y == 0 || y == maxY - 1) return true

        return others.subList(0, x).all { it < first } || others.subList(x + 1, maxX).all { it < first }
    }

    private fun findMaxScore(input: List<List<Int>>): Int {
        maxX = input[0].size
        maxY = input.size

        val scoreMap = List(maxY) { MutableList(maxX) { 1 } }

        setScore(input, scoreMap)

        val rotatedIntMap = rotate90(input, 0)
        val rotatedScoreMap = rotate90(scoreMap, 0)

        maxX = rotatedIntMap[0].size
        maxY = rotatedIntMap.size

        setScore(rotatedIntMap, rotatedScoreMap)

        return rotatedScoreMap.maxOf { it.max() }

    }

    private fun setScore(input: List<List<Int>>, scoreMap: List<MutableList<Int>>) {
        for (i in 0 until maxY) {
            for (j in 0 until maxX) {
                    scoreMap[j][i] = scoreMap[j][i] * calculateScore(input[j][i], input[j], i)
            }
        }
    }

    private fun calculateScore(size: Int, others: List<Int>, x: Int): Int {
        return when (x) {
            0 -> 0
            maxX - 1 -> 0
            else -> {
                countTrees(size, others.subList(0, x).reversed())
                    .times(countTrees(size, others.subList(x + 1, maxX)))
            }
        }
    }

    private fun countTrees(size: Int, others: List<Int>): Int {
        if (others.isEmpty()) return 0
        val count = others.takeWhile { it < size }.count()

        return if (count < others.size) count + 1 else count
    }

}

