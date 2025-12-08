package de.iits.aoc._2022

import de.iits.aoc.util.filledLines
import kotlin.math.absoluteValue

class Day24 {

    private val walls = hashSetOf<Point>()

    //    private val free = hashSetOf<Point>()
    private val blizzards = hashMapOf<Int, Set<Blizzard>>()

    fun calculate(inputStr: String, thereAndBackAgain: Boolean = false): Int {
        parseInput(inputStr)
        val start = Point(2, 1)
        val end = Point(walls.maxOf { it.x } - 1, walls.maxOf { it.y })

        return if (!thereAndBackAgain)
            simulate(start, end)
        else {
            val there = simulate(start, end, 0)
            val back = simulate(end, start, there)
            val again = simulate(start, end, back)
            again
        }
    }

    private fun parseInput(inputStr: String) {
        val blizzard = hashSetOf<Blizzard>()
        inputStr.filledLines()
            .forEachIndexed { y, line ->
                line.forEachIndexed { x, char ->
                    when (char) {
                        '#' -> walls.add(Point(x + 1, y + 1, '█'))
//                        '.' -> free.add(Point(x, y, '.'))
                        '.' -> {} // do nothing
                        else -> {
//                            free.add(Point(x, y, '.'))
                            blizzard.add(createBlizzard(x + 1, y + 1, char))
                        }
                    }
                }
            }
//        walls += Point(2, 0, '█')
        blizzards[0] = blizzard
    }

    private fun createBlizzard(x: Int, y: Int, direction: Char): Blizzard =
        when (direction) {
            '^' -> Blizzard(Point(x, y), Point(0, -1, direction))
            'v' -> Blizzard(Point(x, y), Point(0, 1, direction))
            '<' -> Blizzard(Point(x, y), Point(-1, 0, direction))
            '>' -> Blizzard(Point(x, y), Point(1, 0, direction))
            else -> throw IllegalArgumentException("unknown blizzard type: $direction")
        }

    private fun simulate(start: Point, end: Point, minutes: Int = 0): Int {
        val maxX = walls.maxOf { it.x }
        val maxY = walls.maxOf { it.y }

        val queue = ArrayDeque<MapState>()
        val seen = mutableSetOf<MapState>()
        val startState = MapState(start, minutes)
        queue.addFirst(startState)

        while (queue.isNotEmpty()) {
            val currentState = queue.removeLast()
            if (seen.none {
                    it.position.x == currentState.position.x
                            && it.position.y == currentState.position.y
                            && it.minutes == currentState.minutes
                }) {
                seen.add(currentState)
//                pruneQueue(currentState, end, queue)

//                println("Time: ${currentState.minutes}, position: ${currentState.position.x}, ${currentState.position.y}")

                val nextBlizzards = blizzards.getOrPut(currentState.minutes + 1) {
                    blizzards[currentState.minutes]!!.map { it.move(maxX, maxY) }.toSet()
                }

                val nextPositions = currentState.position.nextPossible(maxY, nextBlizzards.map { it.p }.union(walls))

                for (position in nextPositions) {
                    if (position.sameCoordsAs(end)) {
                        return currentState.minutes + 1
                    }
                    queue.addFirst(MapState(position, currentState.minutes + 1))
                }
//            draw(currentState.position, currentState.blizzards)
            }
        }
        return -1
    }

    private fun pruneQueue(current: MapState, end: Point, queue: ArrayDeque<MapState>) {
        //works for part 1, breaks part two :(
        val distanceLeft = (end.x - current.position.x).absoluteValue + (end.y - current.position.y).absoluteValue
        queue.removeIf {
            it.minutes >= current.minutes
                    && (end.x - it.position.x).absoluteValue + (end.y - it.position.y).absoluteValue > distanceLeft * 2
        }
    }

    private fun draw(point: Point, blizzards: Collection<Blizzard>) {
        val points = mutableListOf<Point>()
        points.addAll(walls)
        points.addAll(blizzards.map { Point(it.p.x, it.p.y, it.direction.height) })
        points.add(point.copy(height = 'X'))
        val mod = points.groupBy { Pair(it.x, it.y) }.map {
            if (it.value.size > 1)
                it.value.first().copy(height = it.value.size.toString().first())
            else it.value.first()
        }
        mod.draw()
    }

}

private data class Blizzard(val p: Point, val direction: Point) {
    fun move(maxX: Int, maxY: Int): Blizzard {
        val target = p.add(direction)
        if (target.x == maxX) target.x = 2
        else if (target.x == 1) target.x = maxX - 1
        else if (target.y == maxY) target.y = 2
        else if (target.y == 1) target.y = maxY - 1

        return this.copy(p = target)
    }

    fun move(maxX: Int, maxY: Int, minutes: Int): Blizzard {
        var curX = p.x
        var curY = p.y
        repeat(minutes) {
            curX += direction.x
            curY += direction.y

            if (curX == maxX) curX = 2
            else if (curX == 1) curX = maxX - 1
            else if (curY == maxY) curY = 2
            else if (curY == 1) curY = maxY - 1
        }

        return this.copy(p = Point(curX, curY))
    }
}

private data class MapState(val position: Point, val minutes: Int)

private fun Point.nextPossible(maxY: Int, obstacles: Set<Point>): List<Point> {
    val left = Point(x - 1, y)
    val right = Point(x + 1, y)
    val up = Point(x, y - 1)
    val down = Point(x, y + 1)

    return setOf(this, left, right, up, down).filter { it.y in 1..maxY }.without(obstacles)
}
