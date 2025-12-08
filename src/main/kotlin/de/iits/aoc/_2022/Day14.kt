package de.iits.aoc._2022

import de.iits.aoc.util.filledLines
import de.iits.aoc.util.join

class Day14 {

    fun calculate(inputStr: String, end: Point?): Int {
        val filledPoints = inputStr.filledLines()
            .flatMap {
                parseInput(it)
                    .windowed(2)
                    .flatMap { ends -> createLines(ends.first(), ends.last()) }
            }
            .distinct().toMutableList()

//        println("minX: ${filledPoints.minOf { it.x }}")
//        println("maxX: ${filledPoints.maxOf { it.x }}")
//        println("maxY: ${filledPoints.maxOf { it.y }}")

        if (end != null) {
            val maxY = filledPoints.maxOf { it.y } + 2
            filledPoints.addAll(createLines(
                Point(filledPoints.minOf { it.x } - maxY, maxY),
                Point(filledPoints.maxOf { it.x } + maxY, maxY)
            ))
        }

        var restingPoint: Point?
        var count = 0
        do {
            restingPoint = fallDown(Point(500, 0), filledPoints)
            if (restingPoint != end) {
                filledPoints.add(restingPoint!!)
                count++
            }
//            if (count % 1000 == 0) {
//                println(count)
//            }
        } while (restingPoint != null && !sameCoords(restingPoint,end))

//        draw(filledPoints)

        return count
    }

    private fun sameCoords(a: Point?, b: Point?) =
        a != null && b!= null && a.x == b.x && a.y == b.y

    private fun parseInput(input: String): List<Point> =
        input.split(" -> ")
            .map {
                with(it.split(","))
                { Point(this.first().toInt(), this.last().toInt()) }
            }

    fun fallDown(start: Point, filled: List<Point>): Point? {
        val pointUnder = filled
            .filter { it.x == start.x && it.y > start.y }
            .minByOrNull { it.y } ?: return null

        val below = filled.filter { it.y == pointUnder.y }
        return when {
            below.none { it.x == start.x - 1 } ->
                fallDown(Point(start.x - 1, start.y - 1), filled.filter { it.y >= pointUnder.y })

            below.none { it.x == start.x + 1 } ->
                fallDown(Point(start.x + 1, start.y - 1), filled.filter { it.y >= pointUnder.y })

            else -> Point(start.x, pointUnder.y - 1, '●')
        }
    }

    private fun draw(filledPoints: List<Point>) {
        val minX = filledPoints.minOf { it.x }
        val maxX = filledPoints.maxOf { it.x }
        val maxY = filledPoints.maxOf { it.y }

        val map = (0..maxY)
            .flatMap {
                createLines(Point(minX - maxY, it), Point(maxX + maxY, it), ' ')
            }.filterNot { containsPoint(it, filledPoints) }
            .toMutableList()

            map.addAll(filledPoints)

        for (i: Int in 0..maxY) {
            println(map.filter { it.y == i }.sortedBy { it.x }.map { it.height }.join())
        }
        println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬")
    }

    private fun containsPoint(point: Point, list: List<Point>)
        = list.any { it.x == point.x && it.y == point.y }
}

