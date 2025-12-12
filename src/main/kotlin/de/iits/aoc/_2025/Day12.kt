package de.iits.aoc._2025

import de.iits.aoc.util.Point
import de.iits.aoc.util.filledLines

class Day12 {

    fun calculate(inputStr: String): Int {
        val input = inputStr.filledLines()

        val blueprints = (0..5).map { readShape(it, input) }
        val treeAreas = input.slice(30 until input.size).map { TreeArea.from(it, blueprints) }

        return treeAreas.count { it.matches() }
    }


    private fun readShape(number: Int, inputLines: List<String>) =
        Shape.fromLines(inputLines.slice((number * 5 + 1) until (number * 5 + 4)))


    fun calculate2(inputStr: String) =
        inputStr.filledLines()


    data class Shape(val points: List<Point>) {
        companion object {
            fun fromLines(inputLines: List<String>): Shape {
                val points = buildList {
                    inputLines.forEachIndexed { y, line ->
                        line.forEachIndexed { x, c ->
                            add(Point(x.toLong(), y.toLong(), c))
                        }
                    }
                }
                return Shape(points)
            }
        }

        override fun toString(): String {
            return """
                ${points[0].type}${points[1].type}${points[2].type}
                ${points[3].type}${points[4].type}${points[5].type}
                ${points[6].type}${points[7].type}${points[8].type}
                """
        }

        fun filledPoints() = points.filter { it.type != '.' }
        fun filledCount() = filledPoints().size
    }

    private data class TreeArea(val width: Int, val height: Int, val toBePlaced: List<Shape>) {
        companion object {
            fun from(inputLine: String, blueprints: List<Shape>): TreeArea {
                val input = inputLine.split(": ")
                val dimensions = input[0].split("x")

                val width = dimensions[0].toInt()
                val height = dimensions[1].toInt()

                val presentsCount = input[1].split(" ").map { it.toInt() }
                val shapes = mutableListOf<Shape>()

                presentsCount.forEachIndexed { index, count ->
                    repeat(count) {
                        shapes.add(blueprints[index])
                    }
                }

                return TreeArea(width, height, shapes)
            }
        }

        fun area() = width * height
        fun matches() = toBePlaced.sumOf { it.filledCount() } <= area()

    }

}

