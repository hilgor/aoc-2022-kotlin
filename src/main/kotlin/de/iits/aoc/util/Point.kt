package de.iits.aoc.util

import kotlin.math.absoluteValue

data class Point(val x: Long, val y: Long, val type: Char = '.') {

    companion object {
       fun fromStringCoords(coordinates: String, delimiter: String = ","): Point {
           val parts = coordinates.split(delimiter)
           require(parts.size >= 2) { "coordinates should be 2-D" }

           val x = parts[0].toLong()
           val y = parts[1].toLong()
           return Point(x, y)
       }
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
