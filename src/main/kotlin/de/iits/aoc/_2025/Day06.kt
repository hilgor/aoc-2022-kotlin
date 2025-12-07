package de.iits.aoc._2025

import filledLines
import removeRegex
import rotate270
import rotate90
import splitOn

class Day06 {

    fun calculate(inputStr: String) =
        inputStr.filledLines()
            .map { it.trim().removeRegex("[\\s]+", ".") }
            .map { it.split(".") }
            .let { rotate90(it, "") }
            .sumOf { solveProblem(it.drop(1), it.first()) }

    private fun solveProblem(problem: List<String>, operator: String): Long = problem
        .map { it.trim().toLong() }
//        .also { println("$it, op: $operator") }
        .let {
            it.drop(1)
                .fold(it.first()) { acc: Long, next -> if (operator == "+") acc + next else acc * next }
//                .also { println(it) }
        }


    fun calculate2(inputStr: String): Long {
        val lines = inputStr.filledLines()
        val operators = lines.last().trim().removeRegex("[\\s]+", ".").split(".").reversed()

        return lines.dropLast(1)
            .map { it.toList() }
            .let { rotate270(it, "") }
            .map { it.joinToString("") }
            .splitOn { it.isBlank() }
            .withIndex()
            .sumOf { (index, problem) ->
                solveProblem(problem, operators[index])
            }
    }
}

