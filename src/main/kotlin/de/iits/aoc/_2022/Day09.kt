package de.iits.aoc._2022

import filledLines
import kotlin.math.absoluteValue
import kotlin.math.sign

class Day09 {

    lateinit var ropeCoords : List<IntArray>
    private val visited = mutableSetOf(Pair(0, 0))

    fun calculate(inputStr: String) :Int {
        ropeCoords = List(2) { IntArray(2) }
        inputStr.filledLines().forEach { move(it, 2) }
        return visited.size
    }

    fun calculate2(inputStr: String) :Int{
        ropeCoords = List(10) { IntArray(2) }
        inputStr.filledLines().forEach { move(it, 10) }
        return visited.size
    }


    private fun move(input: String, size: Int) {
//        println("Move $input")
        val instr = input.split(" ")
        repeat(instr.last().toInt()) {
//            println("---move head")
            when (instr.first()) {
                "R" -> moveHead(1, 0, size)
                "L" -> moveHead(-1, 0, size)
                "U" -> moveHead(0, 1, size)
                "D" -> moveHead(0, -1, size)
            }
        }
    }

    private fun moveHead(x: Int, y: Int, size:Int) {
        ropeCoords[0][0] += x
        ropeCoords[0][1] += y

        for (i in 1 until size){
            val update = moveTail(ropeCoords[i-1][0], ropeCoords[i-1][1], ropeCoords[i])
            ropeCoords[i][0]=update.first
            ropeCoords[i][1]=update.second
        }
        visited.add(Pair(ropeCoords.last()[0], ropeCoords.last()[1]))
    }

    fun moveTail(headX: Int, headY: Int, segmentCoords: IntArray): Pair<Int, Int> {
        val oldX = segmentCoords[0]
        val oldY = segmentCoords[1]
        val xDiff = headX - segmentCoords[0]
        val yDiff = headY - segmentCoords[1]

        if (xDiff.absoluteValue <= 1 && yDiff.absoluteValue <= 1) {
            logMovement(headX, headY, oldX, oldY, segmentCoords)
            return Pair(segmentCoords[0], segmentCoords[1])
        }

        when {
            xDiff.absoluteValue > 1 && yDiff == 0 ->
                segmentCoords[0] = changeCoord(segmentCoords[0], xDiff)

            xDiff.absoluteValue == 0 && yDiff.absoluteValue > 0 ->
                segmentCoords[1] = changeCoord(segmentCoords[1], yDiff)

            xDiff.absoluteValue > 1 && yDiff.absoluteValue > 1 -> {
                segmentCoords[0] = changeCoord(segmentCoords[0], xDiff)
                segmentCoords[1] = changeCoord(segmentCoords[1], yDiff)
            }

            xDiff.absoluteValue > 1 && yDiff.absoluteValue == 1 -> {
                segmentCoords[0] = changeCoord(segmentCoords[0], xDiff)
                segmentCoords[1] = headY
            }

            xDiff.absoluteValue == 1 && yDiff.absoluteValue > 1 -> {
                segmentCoords[0] = headX
                segmentCoords[1] = changeCoord(segmentCoords[1], yDiff)
            }

            else -> {
                println("unknown jump for xDiff $xDiff and yDiff $yDiff: ")
                logMovement(headX, headY, oldX, oldY, segmentCoords)
            }
        }

        logMovement(headX, headY, oldX, oldY, segmentCoords)
        return Pair(segmentCoords[0], segmentCoords[1])
    }

    private fun logMovement(headX: Int, headY: Int, oldX: Int, oldY: Int, segmentCoords: IntArray) {
//        println("head moved to $headX,$headY: segment moves from $oldX,$oldY to ${segmentCoords[0]},${segmentCoords[1]}")
    }

    private fun changeCoord(a: Int, b: Int) =
        a + ((b.absoluteValue - 1) * b.sign)


}

