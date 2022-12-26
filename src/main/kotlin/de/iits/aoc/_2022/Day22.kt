package de.iits.aoc._2022

import filledLines
import join
import java.lang.IllegalArgumentException

class Day22 {

    private val instructions = mutableListOf<Instruction>()
    private val map = mutableListOf<Point3D>()
    private val xMap = mutableMapOf<Int, MutableList<Point3D>>()
    private val yMap = mutableMapOf<Int, MutableList<Point3D>>()

    fun calculate(inputStr: String, cube: Boolean = false, cubeSize: Int = -1): Int {
        parseInput(inputStr.filledLines(), cubeSize)

        var position = yMap[1]!!.first()
        var direction = Direction.RIGHT

        for (instruction in instructions) {
            position = move(position, direction, instruction, cube)
            direction = position.direction
        }

//        draw()
        return position.y.times(1000) + position.x.times(4) + direction.value
    }

    private fun parseInput(input: List<String>, cubeSize: Int) {
        parseMap(input.subList(0, input.size - 2), cubeSize)
        parseInstructions(input.last())
        xMap.values.forEach { it.sortBy { point -> point.y } }
        yMap.values.forEach { it.sortBy { point -> point.x } }
    }

    private fun parseInstructions(instruction: String) {
        val acc = mutableListOf<Char>()
        for (char in instruction.toList()) {
            if (char == 'L' || char == 'R') {
                if (acc.isNotEmpty()) {
                    instructions.add(Instruction(acc.join().toInt()))
                    acc.clear()
                }
                instructions.add(Instruction(rotation = char))
            } else acc.add(char)

        }
        if (acc.isNotEmpty()) instructions.add(Instruction(acc.join().toInt()))
    }

    private fun parseMap(input: List<String>, cubeSize: Int) {
        for (y in input.indices) {
            for (x in input[y].indices) {
                if (!input[y][x].isWhitespace()) {
                    val point = Point3D(x + 1, y + 1, calculateZ(x, y, cubeSize), type = input[y][x])
                    xMap.getOrPut(x + 1) { mutableListOf() }.add(point)
                    yMap.getOrPut(y + 1) { mutableListOf() }.add(point)
                    map.add(point)
                }
            }
        }
    }

    private fun calculateZ(x: Int, y: Int, cubeSize: Int): Int =
        if (cubeSize < 0) 1
        else
            when (y / cubeSize) {
                0 -> when (x / cubeSize) {
                    1 -> 1
                    2 -> 2
                    else -> throw IllegalArgumentException("x -> map to big!")
                }

                1 -> 4

                2 -> when (x / cubeSize) {
                    0 -> 5
                    1 -> 6
                    else -> throw IllegalArgumentException("x -> map to big!")
                }

                3 -> 3

                else -> throw IllegalArgumentException("y -> map to big!")
            }


    private fun move(start: Point3D, direction: Direction, instruction: Instruction, cube: Boolean): Point3D {
        val newDirection = determineDirection(direction, instruction.rotation)
        var current = start
        current.direction = newDirection
        instruction.distance?.let {
            for (i in 0 until it) {
                val moveResult = if (!cube) current.moveToNextField() else current.moveOnCubeToNextField()
                current = moveResult.first
                if (!moveResult.second) break
            }
        }
//        println("Moving in direction ${newDirection.name}, instruction: $instruction")
//        draw()
//        current.direction = newDirection
        return current
    }

    private fun determineDirection(current: Direction, new: Char?): Direction =
        new?.let {
            when (current) {
                Direction.UP -> if (it == 'L') Direction.LEFT else Direction.RIGHT
                Direction.DOWN -> if (it == 'L') Direction.RIGHT else Direction.LEFT
                Direction.LEFT -> if (it == 'L') Direction.DOWN else Direction.UP
                Direction.RIGHT -> if (it == 'L') Direction.UP else Direction.DOWN
            }
        } ?: current

    private fun stepToDirection(direction: Direction): Point3D =
        when (direction) {
            Direction.UP -> Point3D(0, -1, 1)
            Direction.DOWN -> Point3D(0, 1, 1)
            Direction.LEFT -> Point3D(-1, 0, 1)
            Direction.RIGHT -> Point3D(1, 0, 1)
        }

    private fun Point3D.moveToNextField(): Pair<Point3D, Boolean> =
        map.firstOrNull { it.same2DCoords(this.add2D(stepToDirection(direction))) }
            ?.let { moveOrStop(this, it.copy(direction = direction)) }
            ?: when (direction) {
                Direction.UP -> moveOrStop(this, xMap[this.x]!!.last().copy(direction = direction))
                Direction.DOWN -> moveOrStop(this, xMap[this.x]!!.first().copy(direction = direction))
                Direction.LEFT -> moveOrStop(this, yMap[this.y]!!.last().copy(direction = direction))
                Direction.RIGHT -> moveOrStop(this, yMap[this.y]!!.first().copy(direction = direction))
            }

    private fun Point3D.moveOnCubeToNextField(): Pair<Point3D, Boolean> =
        map.firstOrNull { it.same2DCoords(this.add2D(stepToDirection(direction))) }
            ?.let { moveOrStop(this, it.copy(direction = direction)) }
            ?: when (this.z) {
                1 -> when (direction) {
                    Direction.UP -> moveOrStop(this, yMap[100 + this.x]!!.first().copy(direction = Direction.RIGHT))
                    Direction.LEFT -> moveOrStop(this, yMap[151 - this.y]!!.first().copy(direction = Direction.RIGHT))
                    else -> throw IllegalStateException("unexpected move from $this")
                }

                2 -> when (direction) {
                    Direction.UP -> moveOrStop(this, xMap[this.x - 100]!!.last().copy(direction = Direction.UP))
                    Direction.RIGHT -> moveOrStop(this, yMap[151 - this.y]!!.last().copy(direction = Direction.LEFT))
                    Direction.DOWN -> moveOrStop(this, yMap[this.x - 50]!!.last().copy(direction = Direction.LEFT))
                    else -> throw IllegalStateException("unexpected move from $this")
                }

                3 -> when (direction) {
                    Direction.RIGHT -> moveOrStop(this, xMap[this.y - 100]!!.last().copy(direction = Direction.UP))
                    Direction.DOWN -> moveOrStop(this, xMap[100 + this.x]!!.first().copy(direction = Direction.DOWN))
                    Direction.LEFT -> moveOrStop(this, xMap[this.y - 100]!!.first().copy(direction = Direction.DOWN))
                    else -> throw IllegalStateException("unexpected move from $this")
                }

                4 -> when (direction) {
                    Direction.RIGHT -> moveOrStop(this, xMap[50 + this.y]!!.last().copy(direction = Direction.UP))
                    Direction.LEFT -> moveOrStop(this, xMap[this.y - 50]!!.first().copy(direction = Direction.DOWN))
                    else -> throw IllegalStateException("unexpected move from $this")
                }

                5 -> when (direction) {
                    Direction.UP -> moveOrStop(this, yMap[this.x + 50]!!.first().copy(direction = Direction.RIGHT))
                    Direction.LEFT -> moveOrStop(this, yMap[151 - this.y]!!.first().copy(direction = Direction.RIGHT))
                    else -> throw IllegalStateException("unexpected move from $this")
                }

                6 -> when (direction) {
                    Direction.RIGHT -> moveOrStop(this, yMap[151 - this.y]!!.last().copy(direction = Direction.LEFT))
                    Direction.DOWN -> moveOrStop(this, yMap[100 + this.x]!!.last().copy(direction = Direction.LEFT))
                    else -> throw IllegalStateException("unexpected move from $this")
                }

                else -> throw IllegalStateException("unknown cube side: $this")
            }


    private fun moveOrStop(start: Point3D, end: Point3D): Pair<Point3D, Boolean> =
        if (end.type == '.') end to true
        else start to false

    private fun draw() {

        for (y in 1..yMap.keys.max()) {
            val line = mutableListOf<Char>()
            for (x in 1..xMap.keys.max()) {
                line.add(map.firstOrNull { x == it.x && y == it.y }?.type ?: ' ')
            }
            println(line.join())
        }
    }

}


private data class Instruction(
    val distance: Int? = null,
    val rotation: Char? = null
)
