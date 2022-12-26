package de.iits.aoc._2022

data class Point3D(var x: Int, var y: Int, var z: Int, var distance: Int = 0, var type: Char = '.', var direction: Direction = Direction.RIGHT)

fun Point3D.sameCoords(other: Point3D) = x == other.x && y == other.y && z == other.z
fun Point3D.same2DCoords(other: Point3D) = x == other.x && y == other.y
fun Point3D.partOf(list: List<Point3D>) = list.any { sameCoords(it) }
fun Point3D.add(other: Point3D) = copy(x = x + other.x, y = y + other.y, z = z + other.z)
fun Point3D.add2D(other: Point3D) = copy(x = x + other.x, y = y + other.y)

enum class Direction(val value: Int, val char: Char) {
    RIGHT(0, '>'), DOWN(1, 'v'), LEFT(2, '<'), UP(3, '^')
}