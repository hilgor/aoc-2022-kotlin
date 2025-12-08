package de.iits.aoc._2025

import de.iits.aoc.util.filledLines

class Day04 {

    fun calculate(inputStr: String) =
        countAccessibleRolls(convertToGrid(inputStr.filledLines()))

    private fun convertToGrid(input: List<String>): Array<IntArray> =
        Array(input.size) { y ->
            IntArray(input[y].length) { x ->
                when (input[y][x]) {
                    '@' -> 1
                    '.' -> 0
                    else -> 0
                }
            }
        }

    private fun countAccessibleRolls(grid: Array<IntArray>, allowedMax: Int = 3, remove: Boolean = false): Int {
        var count = 0

        for (y in grid.indices) {
            for (x in grid[y].indices) {
                if (grid[y][x] == 1) {
                    // Found a roll, count neighbors
                    countNeighbors(grid, y, x)
                        .takeIf { it <= allowedMax }
                        ?.let {
                            if (remove) grid[y][x] = 0
                            count++
                        }
                }
            }
        }

        return count
    }

    private fun countNeighbors(grid: Array<IntArray>, y: Int, x: Int, distance: Int = 1): Int =
        (-distance..distance).sumOf { dy ->
            (-distance..distance).sumOf { dx ->
                if (dy == 0 && dx == 0) 0
                else grid.getOrDefault(y + dy, x + dx)
            }
        }

    private fun Array<IntArray>.getOrDefault(y: Int, x: Int, default: Int = 0): Int =
        if (y in indices && x in this[y].indices) this[y][x] else default


    fun calculate2(inputStr: String): Int {
        var count = 0
        var lastRun = -1

        val grid = convertToGrid(inputStr.filledLines())

        while (lastRun != count) {
            lastRun = count
            count += countAccessibleRolls(grid, remove = true)
        }

        return count
    }

}



