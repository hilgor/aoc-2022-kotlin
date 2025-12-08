package de.iits.aoc._2022

import de.iits.aoc.util.filledLines
import kotlin.math.absoluteValue

class Day12 {

    fun calculate(inputStr: String, startPoints: String): Int {
        val charMap = inputStr.filledLines()
            .map { it.toList() }

        val map = List(charMap.size) { mutableListOf<Point>() }
        for (i in charMap.indices) {
            for (j in charMap[0].indices) {
                if (startPoints.toList().contains(charMap[i][j]))
                    map[i].add(Point(j, i, charMap[i][j], 0))
                else
                    map[i].add(Point(j, i, charMap[i][j]))
            }
        }

        calculateShortestPath('E', map)

        return map.flatten().first { it.height == 'E' }.distance
    }


    fun calculate2(inputStr: String) =
        inputStr.filledLines()

    private fun calculateShortestPath(end: Char, map: List<List<Point>>) {
        val unvisited = map.flatten().sortedBy { it.distance }.toMutableList()
        var current = unvisited.first()

        while (unvisited.isNotEmpty() &&
            (current.height != end || current.distance < Integer.MAX_VALUE)
        ) {
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

    private fun findNeighbours(point: Point, candidates: List<Point>): List<Point> =
        candidates
            .filter {
                (point.x - it.x).absoluteValue == 1 && (point.y - it.y).absoluteValue == 0
                        || (point.x - it.x).absoluteValue == 0 && (point.y - it.y).absoluteValue == 1
            }
            .filter { point.canStepTo(it) }


}




