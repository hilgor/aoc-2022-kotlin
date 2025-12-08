package de.iits.aoc._2022

import de.iits.aoc.util.join
import de.iits.aoc.util.nth

class Day17 {

    private lateinit var wind: List<Int>
    private val filled = createLines(Point(1, 0), Point(7, 0), '#').toMutableList()

    private val rock1 = listOf(Pair(0, 0), Pair(1, 0), Pair(2, 0), Pair(3, 0))
    private val rock2 = listOf(Pair(1, 0), Pair(0, 1), Pair(1, 1), Pair(2, 1), Pair(1, 2))
    private val rock3 = listOf(Pair(0, 0), Pair(1, 0), Pair(2, 0), Pair(2, 1), Pair(2, 2))
    private val rock4 = listOf(Pair(0, 0), Pair(0, 1), Pair(0, 2), Pair(0, 3))
    private val rock5 = listOf(Pair(0, 0), Pair(1, 0), Pair(0, 1), Pair(1, 1))
    private val figureDeltas = arrayListOf(rock1, rock2, rock3, rock4, rock5)
    private var windCount = 0
    private var figureCount = 0



    fun calculate(inputStr: String): Int {
        parseLine(inputStr)

        repeat(2022) {
            moveNextFigure()
        }

//        draw(filled)
        return filled.highest()
    }

    private fun parseLine(input: String) {
        wind = input.trim().toList().map { if (it == '<') -1 else 1 }
    }

    private fun getNextWind(): Int {
        return wind.nth(windCount++)
    }

    private fun moveToSide(figure: List<Point>) {
        val wind = getNextWind()

        if (figure.left() + wind < 1 || figure.right() + wind > 7) return

        val relevant = filled.range(figure.highest(), figure.lowest())
        figure.forEach { Point ->
            if (relevant.any { it.x == Point.x + wind && it.y == Point.y }) return
        }

        figure.forEach { it.x += wind }
    }

    private fun moveDown(figure: List<Point>): Boolean {
        val relevant = filled.range(figure.highest(), figure.lowest() - 1)
        var canMove = true
        figure.forEach { Point ->
            if (relevant.any { it.x == Point.x && it.y == Point.y - 1 }) {
                canMove = false
            }
        }

        if (canMove) figure.forEach { it.y -= 1 }
        else filled.addAll(figure)

        return canMove
    }

    private fun getNextFigure(top: Int): List<Point> {
        val next = figureDeltas.nth(figureCount++)

        return next.map { Point(3 + it.first, top + 4 + it.second, '█') }
    }

    private fun moveNextFigure() {
        var moving = true
        val figure = getNextFigure(filled.highest())
        while (moving) {
            moveToSide(figure)
            moving = moveDown(figure)
        }
    }

    private fun calculateHeight(targetFigureCount: Long): Long {
        data class State(val ceiling: List<Int>, val figureMod: Int, val windMod: Int)

        val seen: MutableMap<State, Pair<Int, Int>> = mutableMapOf()
        while (true) {
            moveNextFigure()
            val state = State(filled.normalizedCaveCeiling(), figureCount % figureDeltas.size, windCount % wind.size)
            if (state in seen) {
                // Fast forward
                val (figureCountAtLoopStart, heightAtLoopStart) = seen.getValue(state)
                val figuresPerLoop: Long = figureCount - 1L - figureCountAtLoopStart
                val totalLoops: Long = (targetFigureCount - figureCountAtLoopStart) / figuresPerLoop
                val remainingBlocksFromClosestLoopToGoal: Long =
                    (targetFigureCount - figureCountAtLoopStart) - (totalLoops * figuresPerLoop)
                val heightGainedSinceLoop = filled.highest() - heightAtLoopStart
                repeat(remainingBlocksFromClosestLoopToGoal.toInt()) {
                    moveNextFigure()
                }
                return filled.highest() + (heightGainedSinceLoop * (totalLoops - 1))
            }
            seen[state] = figureCount - 1 to filled.highest()
        }
    }


    fun calculate2(inputStr: String) : Long {
        parseLine(inputStr)
        return calculateHeight(1000000000000L - 1)
    }



}

private fun draw(filledPoints: List<Point>) {
    val minX = 0
    val maxX = 9
    val maxY = filledPoints.maxOf { it.y } + 2

    val map = (0..maxY)
        .flatMap {
            createLines(Point(1, it), Point(7, it), ' ') +
                    listOf(Point(0, it, '#'), Point(9, it, '#'), Point(-1, it, it.toString().first()))
        }.filterNot { filledPoints.containsPoint(it) }
        .toMutableList()

    map.addAll(filledPoints)

    for (i in maxY.downTo(1)) {
        println(map.filter { it.y == i }.sortedBy { it.x }.map { it.height }.join())
    }
    println("##########")
}

private fun List<Point>.right() = maxOf { it.x }
private fun List<Point>.left() = minOf { it.x }
private fun List<Point>.lowest() = minOf { it.y }
private fun List<Point>.highest() = maxOfOrNull { it.y } ?: 0
private fun List<Point>.bottom() = filter { it.y == this.lowest() }
private fun List<Point>.range(top: Int, bottom: Int) =
    this.filter { it.y in bottom..top }


private fun List<Point>.normalizedCaveCeiling(): List<Int> =
    groupBy { it.x }
        .entries
        .sortedBy { it.key }
        .map { pointList -> pointList.value.maxBy { point -> point.y } }
        .let {
            val normalTo = this.highest()
            it.map { point -> normalTo - point.y }
        }