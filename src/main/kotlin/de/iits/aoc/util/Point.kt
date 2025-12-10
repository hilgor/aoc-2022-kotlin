package de.iits.aoc.util

import kotlin.math.absoluteValue

data class Point(var x: Long, var y: Long, var type: Char = '.') {

    constructor(coordinates: String, delimiter: String = ",") : this(0, 0) {
        val parts = coordinates.split(delimiter)
        require(parts.size >= 2) { "coordinates should be 2-D" }

        x = parts[0].toLong()
        y = parts[1].toLong()
    }

    fun sameCoords(other: Point) = x == other.x && y == other.y
    fun sameX(other: Point) = x == other.x
    fun sameY(other: Point) = y == other.y

    fun squareAreaTo(other: Point, inclusive: Boolean = false): Long {
        val factor = if (inclusive) 1 else 0

        val width = (other.x - x).absoluteValue + factor
        val height = (other.y - y).absoluteValue + factor
        return width * height
    }

}
