package de.iits.aoc._2022

import de.iits.aoc.util.filledLines
import de.iits.aoc.util.splitToInts
import kotlin.math.absoluteValue

class Day18(inputStr: String) {

    private val points = inputStr.filledLines()
        .map { it.splitToInts(",") }
        .map { Point3D(it[0], it[1], it[2]) }

    fun calculate(): Int {
        return points.sumOf { countSides(it, points) }
    }

    fun countSides(point: Point3D, others: List<Point3D>): Int {
        return 6 - (
                others.any { point.x - 1 == it.x && point.y == it.y && point.z == it.z }.toInt()
                    .plus(others.any { point.x + 1 == it.x && point.y == it.y && point.z == it.z }.toInt())
                    .plus(others.any { point.y - 1 == it.y && point.x == it.x && point.z == it.z }.toInt())
                    .plus(others.any { point.y + 1 == it.y && point.x == it.x && point.z == it.z }.toInt())
                    .plus(others.any { point.z - 1 == it.z && point.y == it.y && point.x == it.x }.toInt())
                    .plus(others.any { point.z + 1 == it.z && point.y == it.y && point.x == it.x }.toInt()))
    }


    private fun calculateShortestPath(start: Point3D, input: List<Point3D>) {
        input.forEach { it.distance = Int.MAX_VALUE }
        start.distance = 0

        val unvisited = input.sortedBy { it.distance }.toMutableList()
        var current = input.first()

        while (unvisited.isNotEmpty() && current.distance < Integer.MAX_VALUE) {
            findNeighbours(current, unvisited)
                .forEach {
                    val newDistance = current.distance + 1
                    it.distance = if (newDistance < it.distance) newDistance else it.distance
                }
            unvisited.remove(current)
            unvisited.sortBy { it.distance }
            if (unvisited.isNotEmpty())
                current = unvisited.first()
        }
    }

    private fun findNeighbours(point: Point3D, candidates: List<Point3D>): List<Point3D> =
        candidates
            .filterNot { it.partOf(points) }
            .filter {
                ((point.x - it.x).absoluteValue == 1 && (point.y == it.y) && (point.z == it.z))
                        || ((point.y - it.y).absoluteValue == 1 && (point.x == it.x) && (point.z == it.z))
                        || ((point.z - it.z).absoluteValue == 1 && (point.y == it.y) && (point.x == it.x))
            }


    fun calculate2(): Int {
        val minX = points.minOf { it.x } - 1
        val maxX = points.maxOf { it.x } + 1
        val minY = points.minOf { it.y } - 1
        val maxY = points.maxOf { it.y } + 1
        val minZ = points.minOf { it.z } - 1
        val maxZ = points.maxOf { it.z } + 1

        val freePoints = mutableListOf<Point3D>()
        for (x in minX..maxX) {
            for (y in minY..maxY) {
                for (z in minZ..maxZ) {
                    freePoints.add(Point3D(x, y, z))
                }
            }
        }

        val start = freePoints.first { it.x == minX && it.y == minY && it.z == minZ }
        calculateShortestPath(start, freePoints)

        val notReachable = freePoints.filter { it.distance == Int.MAX_VALUE }

        return notReachable.sumOf { countSides(it, notReachable) }
    }


}

private fun Boolean.toInt() = if (this) 1 else 0



