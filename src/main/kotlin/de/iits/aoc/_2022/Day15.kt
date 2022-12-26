package de.iits.aoc._2022

import filledLines
import java.lang.Integer.max
import java.lang.Integer.min
import kotlin.math.absoluteValue

class Day15 {

    fun calculate(inputStr: String, row: Int): Int {
        return inputStr.filledLines()
            .map { parseInputLine(it) }
            .flatMap { computeCoveredInRow(it.first, it.second, row) }
            .distinct()
            .count()
    }


    private fun parseInputLine(input: String): Pair<Point, Point> {
        val parts = input.split(": ")
        val sensor = parts.first().removePrefix("Sensor at x=")
            .split(", y=")
            .run { Point(this.first().toInt(), this.last().toInt(), 'S') }
        val beacon = parts.last().removePrefix("closest beacon is at x=")
            .split(", y=")
            .run { Point(this.first().toInt(), this.last().toInt(), 'B') }

        return Pair(sensor.copy(distance = computeDistance(sensor, beacon)), beacon)
    }

    fun computeCoveredInRow(
        sensor: Point, beacon: Point, row: Int, excludeBeacon: Boolean = true,
        lowerBound: Int = Int.MIN_VALUE, upperBound: Int = Int.MAX_VALUE
    ): List<Int> {
        val xDistance = computeDistance(sensor, beacon) - ((sensor.y - row).absoluteValue)
//        println("distance: ${computeDistance(sensor, beacon)}, diff: ${(sensor.y - row)}, xDistance: $xDistance")

        if (xDistance < 0) {
            return emptyList()
        }

        val minX = sensor.x - xDistance
        val maxX = sensor.x + xDistance
        if (maxX < lowerBound || minX > upperBound) {
            return emptyList()
        }

        val xList = (max(lowerBound, minX)..min(upperBound, maxX)).toList()
        return if (excludeBeacon && beacon.y == row) xList.minus(beacon.x) else xList
    }

    private fun computeDistance(start: Point, end: Point) =
        (start.x - end.x).absoluteValue + (start.y - end.y).absoluteValue


    fun calculate2(inputStr: String, bound: Int): Long {
        val pairs = inputStr.filledLines()
            .map { parseInputLine(it) }

        val sensors = pairs.map { it.first }

        for (row in 0..bound) {
            val hole = findCoverageHole(sensors, row, 0, bound)
            if (hole != -1) return (hole.toLong() * 4000000) + row
//            if (row % 100000 == 0) println("row: $row")
        }

        return -1
    }

    private fun findCoverageHole(sensors: List<Point>, row: Int, lowerBound: Int, upperBound: Int): Int {

        val ranges = mutableSetOf<Pair<Int, Int>>()
        for (sensor in sensors) {
            val xDistance = sensor.distance - ((sensor.y - row).absoluteValue)

            if (xDistance < 0) continue

            val minX = sensor.x - xDistance
            val maxX = sensor.x + xDistance

            if (maxX < lowerBound || minX > upperBound) continue

            ranges.add(Pair(max(lowerBound, minX), min(upperBound, maxX)))
        }

        return findHoleInRanges(ranges.sortedBy { it.first }, lowerBound, upperBound)
    }

    private fun findHoleInRanges(ranges: List<Pair<Int, Int>>, lowerBound: Int, upperBound: Int): Int {
        if (ranges.first().first > lowerBound) return lowerBound

        var xMax = ranges.first().second

        for (range in ranges) {
            if (range.first > xMax + 1) return xMax + 1
            if (xMax < range.second) xMax = range.second
        }

        return if (xMax < upperBound) upperBound else -1
    }


    fun computeUncoveredInRow(sensor: Point, row: Int, lowerBound: Int, upperBound: Int): List<Int> {
        val xDistance = sensor.distance - ((sensor.y - row).absoluteValue)

        if (xDistance < 0) {
            return listOf(-1)
        }

        val minX = sensor.x - xDistance
        val maxX = sensor.x + xDistance
        if (maxX < lowerBound || minX > upperBound) {
            return listOf(-1)
        }

        return (lowerBound until max(lowerBound, minX)).toList() +
                ((min(upperBound, maxX) + 1)..upperBound).toList()

    }


}



