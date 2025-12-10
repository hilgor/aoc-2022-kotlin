package de.iits.aoc._2025

import de.iits.aoc.util.Point
import de.iits.aoc.util.filledLines
import kotlin.math.max
import kotlin.math.min

class Day09 {

    fun calculate(inputStr: String): Long =
        calculateMaxArea(inputStr.filledLines().map(::Point))

    private fun calculateMaxArea(tiles: List<Point>): Long =
        tiles.foldIndexed(0L) { index, currentMax, currentTile ->
            tiles.drop(index + 1)
                .map { other -> currentTile.squareAreaTo(other, true) }
                .fold(currentMax) { previousMax, area -> max(previousMax, area) }
        }

    fun calculate2(inputStr: String): Long {
        val redTiles = inputStr.filledLines().map(::Point)
        val greenTiles = addGreenTiles(redTiles)

        val xMap = (redTiles + greenTiles).groupBy { it.x }.mapValues { (_, list) -> list.map { it.y }.sorted() }
        val yMap = (redTiles + greenTiles).groupBy { it.y }.mapValues { (_, list) -> list.map { it.x }.sorted() }

        val areas = calculateAreasFor(redTiles)

        println("Starting comparison for ${areas.size} areas")

        areas.forEachIndexed() { index, (area, p1, p2) ->
            val xLyU = Point(min(p1.x, p2.x), min(p1.y, p2.y))
            val xRyU = Point(max(p1.x, p2.x), min(p1.y, p2.y))
            val xLyD = Point(min(p1.x, p2.x), max(p1.y, p2.y))
            val xRyD = Point(max(p1.x, p2.x), max(p1.y, p2.y))

            if (xLyU.isInside(xMap, yMap) && xRyU.isInside(xMap, yMap) &&
                xLyD.isInside(xMap, yMap) && xRyD.isInside(xMap, yMap)
            ) {
                val outlineTiles = addGreenTiles(listOf(xLyU, xRyU, xRyD, xLyD))
                if (outlineTiles.any { !it.isInside(xMap, yMap) }) {
//                 println("Area $area between $p1 and $p2 is invalid due to outline tile outside: $outlineTiles")
                    //do nothing
                } else {
                    return area
                }
            }
        }

        return 0L
    }

    private fun addGreenTiles(redTiles: List<Point>): List<Point> {
        val greenTiles = mutableListOf<Point>()

        redTiles.foldIndexed(greenTiles) { index, greenTiles, tile ->
            val nextTile = if (index + 1 < redTiles.size) redTiles[index + 1] else redTiles[0]
            if (tile.sameX(nextTile)) {
                val yRange = min(tile.y, nextTile.y) + 1 until max(tile.y, nextTile.y)
                yRange.forEach { greenTiles.add(Point(tile.x, it, 'G')) }
            } else if (tile.sameY(nextTile)) {
                val xRange = min(tile.x, nextTile.x) + 1 until max(tile.x, nextTile.x)
                xRange.forEach { greenTiles.add(Point(it, tile.y, 'G')) }
            }
            greenTiles
        }

//        println("Current green tiles: $greenTiles")
//        println("Total green tiles: ${greenTiles.size}")
        return greenTiles
    }

    private fun Point.isInside(xMap: Map<Long, List<Long>>, yMap: Map<Long, List<Long>>): Boolean {
        val yValues = xMap[x] ?: return false
        val xValues = yMap[y] ?: return false

        return xValues.first() <= x && x <= xValues.last() &&
                yValues.first() <= y && y <= yValues.last()
    }

    private fun calculateAreasFor(tiles: List<Point>): List<Triple<Long, Point, Point>> =
        tiles.foldIndexed(mutableListOf<Triple<Long, Point, Point>>()) { index, list, currentTile ->
            tiles.drop(index + 1)
                .map { other -> Triple(currentTile.squareAreaTo(other, true), currentTile, other) }
                .fold(list) { list, area -> list.add(area); list }
        }
            .sortedByDescending { it.first }


}

