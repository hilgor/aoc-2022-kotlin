package de.iits.aoc._2022

import de.iits.aoc.util.filledLines

class Day23(inputStr: String) {

    private val elves = parseInput(inputStr.filledLines())
    private val xMap = mutableMapOf<Int, List<Point>>()
    private val yMap = mutableMapOf<Int, List<Point>>()
    private val directions = listOf(::proposeNorth, ::proposeSouth, ::proposeWest, ::proposeEast)
    private var counter = 0

    fun calculate(): Int {

        repeat(10) {
            simulateRound()
//            println("\nEnd of round $counter\n")
//            elves.draw('.')
        }

//        elves.draw('.')
        val smallest = (elves.maxOf { it.x } - elves.minOf { it.x } + 1) * (elves.maxOf { it.y } - elves.minOf { it.y } + 1)

        return smallest - elves.size
    }

    private fun simulateRound() : Boolean {
        rebuildMaps()
        val proposals = mutableMapOf<Point, MutableList<Point>>()

        elves.filter { it.otherElvesNearby() }.forEach {
            var proposal: Point?
            for (offset in 0..3) {
                proposal = directions.nth(counter + offset)(it)
                if (proposal != null) {
                    proposals.getOrPut(proposal) { mutableListOf() }.add(it)
                    break
                }
            }
        }
        proposals.filter { it.value.size == 1 }.forEach {
            it.value[0].x = it.key.x
            it.value[0].y = it.key.y
        }
        counter++
        return proposals.isNotEmpty()
    }


    private fun parseInput(input: List<String>): List<Point> {
        val result = mutableListOf<Point>()
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                if (char == '#') result.add(Point(x, y, '#'))
            }
        }

        return result
    }

    private fun rebuildMaps() {
        xMap.clear()
        yMap.clear()
        xMap.putAll(elves.groupBy { it.x })
        yMap.putAll(elves.groupBy { it.y })
    }

    private fun Point.otherElvesNearby() =
        elves.any { (it.y != y || it.x != x) && (it.y in y - 1..y + 1) && (it.x in x - 1..x + 1) }


    private fun proposeNorth(position: Point): Point? =
        if (yMap.getOrElse(position.y - 1) { listOf() }.none { it.x in position.x - 1..position.x + 1 })
            position.copy(y = position.y - 1) else null

    private fun proposeSouth(position: Point): Point? =
        if (yMap.getOrElse(position.y + 1) { listOf() }.none { it.x in position.x - 1..position.x + 1 })
            position.copy(y = position.y + 1) else null

    private fun proposeWest(position: Point): Point? =
        if (xMap.getOrElse(position.x - 1) { listOf() }.none { it.y in position.y - 1..position.y + 1 })
            position.copy(x = position.x - 1) else null

    private fun proposeEast(position: Point): Point? =
        if (xMap.getOrElse(position.x + 1) { listOf() }.none { it.y in position.y - 1..position.y + 1 })
            position.copy(x = position.x + 1) else null


    fun calculate2(): Int {

        var hadToMove = true
        while (hadToMove) {
           hadToMove = simulateRound()
        }

        return counter
    }


}

