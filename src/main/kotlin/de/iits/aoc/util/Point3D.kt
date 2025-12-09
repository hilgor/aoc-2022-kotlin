package de.iits.aoc.util

import kotlin.math.absoluteValue

data class Point3D(var x: Int, var y: Int, var z: Int, var distance: Int = 0, var type: Char = '.', var direction: Direction = Direction.RIGHT) {

    constructor(coordinates: String, delimiter: String = ",") : this(0, 0, 0) {
        val parts = coordinates.split(delimiter)
        require(parts.size >= 3) { "coordinates should be 3-D" }

        x = parts[0].toInt()
        y = parts[1].toInt()
        z = parts[2].toInt()
    }

    fun sameCoords(other: Point3D) = x == other.x && y == other.y && z == other.z
    fun same2DCoords(other: Point3D) = x == other.x && y == other.y
    fun partOf(list: List<Point3D>) = list.any { sameCoords(it) }
    fun add(other: Point3D) = copy(x = x + other.x, y = y + other.y, z = z + other.z)
    fun add2D(other: Point3D) = copy(x = x + other.x, y = y + other.y)

    fun euclideanDistanceTo(other: Point3D): Long {
        val dx = (x - other.x).absoluteValue.toLong()
        val dy = (y - other.y).absoluteValue.toLong()
        val dz = (z - other.z).absoluteValue.toLong()
        return dx * dx + dy * dy + dz * dz
    }


    fun calculateShortestPath(input: List<Point3D>) {
        input.forEach { it.distance = Int.MAX_VALUE }
        this.distance = 0

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
//            .filterNot { it.partOf(points) }
            .filter { candidate ->
                ((point.x - candidate.x).absoluteValue == 1 && (point.y == candidate.y) && (point.z == candidate.z))
                        || ((point.y - candidate.y).absoluteValue == 1 && (point.x == candidate.x) && (point.z == candidate.z))
                        || ((point.z - candidate.z).absoluteValue == 1 && (point.y == candidate.y) && (point.x == candidate.x))
            }
}


enum class Direction(val value: Int, val char: Char) {
    RIGHT(0, '>'), DOWN(1, 'v'), LEFT(2, '<'), UP(3, '^')
}