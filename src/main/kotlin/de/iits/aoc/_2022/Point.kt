package de.iits.aoc._2022

import join
import kotlin.math.max
import kotlin.math.min

data class Point(
    var x: Int, var y: Int, val height: Char = 'a',
    var distance: Int = Int.MAX_VALUE, var visited: Boolean = false
) {
    fun getRelHeight() = when (height) {
        'S' -> 'a'
        'E' -> 'z'
        else -> height
    }

    fun sameCoordsAs(other: Point) = this.x == other.x && this.y == other.y
}


fun Point.canStepTo(other: Point) =
    this.getRelHeight() > other.getRelHeight()
            || this.getRelHeight() == other.getRelHeight()
            || this.getRelHeight().inc() == other.getRelHeight()


fun createLines(start: Point, end: Point, color: Char = '█'): List<Point> =
    if (start.x == end.x) {
        (min(start.y, end.y)..max(start.y, end.y))
            .map { Point(start.x, it, color) }
    } else {
        (min(start.x, end.x)..max(start.x, end.x))
            .map { Point(it, start.y, color) }
    }

fun List<Point>.containsPoint(point: Point) = this.any { it.x == point.x && it.y == point.y }

fun Point.add(other: Point): Point = copy(x = x + other.x, y = y + other.y)

fun List<Point>.draw(filler: Char = ' ') {
    val minX = minOf { it.x }
    val maxX = maxOf { it.x }
    val minY = minOf { it.y }
    val maxY = maxOf { it.y }

    val map = (minY..maxY)
        .flatMap {
            createLines(Point(minX, it), Point(maxX, it), filler)
        }.filterNot { containsPoint(it) }
        .toMutableList()

    map.addAll(this)

    for (i: Int in minY..maxY) {
        println(map.filter { it.y == i }.sortedBy { it.x }.map { it.height }.join())
    }
//    println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬")
}

fun Collection<Point>.without(others: Collection<Point>) =
    this.filterNot { others.any { other -> it.x == other.x && it.y == other.y }}