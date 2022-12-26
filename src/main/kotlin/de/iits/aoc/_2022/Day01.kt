package de.iits.aoc._2022

class Day01 {

    fun calculate (input: String, maxCount: Int) : Int {
        val caloriesList = input.trim()
            .split("\n\n")
            .map { list -> list.lines().sumOf { it.toInt() } }

        return caloriesList.sorted().takeLast(maxCount).sum()
    }
}