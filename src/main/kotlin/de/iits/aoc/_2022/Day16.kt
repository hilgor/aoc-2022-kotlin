package de.iits.aoc._2022

import filledLines
import kotlin.math.max

class Day16 {

    lateinit var distanceMap: Map<Pair<String, String>, Int>

    fun calculate(inputStr: String): Int {
        val valves = parseInput(inputStr)

        val visit = visit(valves.first { it.name == "AA" }, 30, valves.filterNot { it.flow == 0 })
        return visit.first
    }

    fun calculate2(inputStr: String): Int {
        val valves = parseInput(inputStr)

        val start = valves.first { it.name == "AA" }

        val relevantValves = valves.filterNot { it.flow == 0 }
        val possiblePaths = visit2(start, 26, relevantValves)
            // theory is, that each player can visit roughly half of the relevant rooms
            .filter { it.second.size >= relevantValves.size / 2 - 1  }
        println("Paths found: ${possiblePaths.size}")
//        possiblePaths.forEach { println(it.second) }

        var max = 0

        for (path in possiblePaths) {
            val complement = possiblePaths
                .filterNot { path.second.any { element -> it.second.contains(element) } }
                .maxOfOrNull { it.first } ?: 0
            if (path.first + complement > max) max = path.first + complement
        }

        return max
    }

    private fun visit(start: Valve, time: Int, valves: List<Valve>): Pair<Int, List<Valve>> {
        if (time <= 1) return Pair(0, listOf())

        val distances = mutableListOf<Pair<Int, List<Valve>>>()

        for (valve in valves) {
            val flow = getFlow(valve.flow, distanceMap[Pair(start.name, valve.name)]!!, time)
            val maxVisit = visit(valve, flow.second, valves.minus(valve))
            distances.add(Pair(flow.first + maxVisit.first, listOf(valve) + maxVisit.second))
        }

        val result = distances.maxByOrNull { it.first } ?: Pair(0, listOf())
//        println("${result.second.name} -> ")
        return result
    }

    private fun visit2(start: Valve, time: Int, valves: List<Valve>): Set<Pair<Int, List<String>>> {
        if (time <= 1) return setOf(Pair(0, listOf()))

//        val distances = mutableListOf<Pair<Int, List<Valve>>>()

        return valves
            .map { valve ->
                val flow = getFlow(valve.flow, distanceMap[Pair(start.name, valve.name)]!!, time)
                val paths = visit2(valve, flow.second, valves.minus(valve))

                listOf(Pair(flow.first, listOf(valve.name))) + paths
                    .map { path ->
                        Pair(flow.first + path.first, listOf(valve.name) + path.second)
                    }
//                    .toList()
            }
//            .toList()
            .flatten().toSet()
    }

//    private fun visit2(start: Valve, time: Int, valves: List<Valve>): List<Pair<Int, List<Valve>>> {
//        if (time <= 1) return listOf(Pair(0, listOf()))
//
////        val distances = mutableListOf<Pair<Int, List<Valve>>>()
//
//        return valves
//            .map {valve ->
//                val flow = getFlow(valve.flow, distanceMap[Pair(start.name, valve.name)]!!, time)
//                val paths = visit2(valve, flow.second, valves.minus(valve))
//
//                listOf(Pair(flow.first, listOf(valve))) + paths
//                    .map { path ->
//                        Pair(flow.first + path.first, listOf(valve) + path.second)
//                    }.toList()
//            }
//            .toList().flatten()
//    }

    private fun getFlow(flow: Int, distance: Int, time: Int): Pair<Int, Int> =
        with(time - (distance + 1)) { Pair(max(this, 0) * flow, this) }

    private fun parseInput(inputStr: String): List<Valve> {
        val valves = inputStr.filledLines().map(::parseLine)
        distanceMap = calculateDistanceMap(valves)
        return valves
    }

    private fun parseLine(input: String): Valve {
        with(input.split("; tunnels lead to valves ", "; tunnel leads to valve ")) {
            val valveDesc = this.first().removePrefix("Valve ").split(" ")
            val name = valveDesc.first()
            val flow = valveDesc[3].removePrefix("rate=").toInt()

            val tunnels = this.last().split(", ")

            return Valve(name, flow, tunnels)
        }

    }

    private fun calculateDistanceMap(valves: List<Valve>): Map<Pair<String, String>, Int> {
        val result = mutableMapOf<Pair<String, String>, Int>()

        for (start in valves) {
            for (end in valves) {
                result[Pair(start.name, end.name)] =
                    if (start.name == end.name) 0
                    else result.getOrElse(Pair(end.name, start.name)) {
                        calculateShortestPath(start.name, end.name, valves)
                    }
            }
        }
        return result
    }

    private fun calculateShortestPath(start: String, end: String, valves: List<Valve>): Int {
        valves.forEach { it.distance = Int.MAX_VALUE }
        var current = valves.first { it.name == start }
        current.distance = 0

        val unvisited = valves.sortedBy { it.distance }.toMutableList()

        while (unvisited.isNotEmpty() &&
            (current.name != end)
        ) {
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

        return if (current.name == end) current.distance else Int.MAX_VALUE
    }

    private fun findNeighbours(valve: Valve, candidates: List<Valve>): List<Valve> =
        candidates.filter { valve.tunnelsTo.contains(it.name) }

}


data class Valve(
    val name: String,
    val flow: Int,
    val tunnelsTo: List<String>,
    var distance: Int = 0
)