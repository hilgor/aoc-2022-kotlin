package de.iits.aoc._2025

import de.iits.aoc.util.Point3D
import de.iits.aoc.util.filledLines
import java.util.TreeMap

class Day08 {

    fun calculate(inputStr: String, connections: Int): Int {
        val nodes = inputStr.filledLines().map { Point3D(it) }
        val circuits = nodes.map { mutableSetOf(it) }.toMutableList()

        val distanceMap = computeDistanceMap(nodes, circuits, connections)
        mergeCircuits(distanceMap, circuits, nodes.size)

        return circuits
            .map { it.size }
            .sortedDescending()
            .take(3)
//            .also { println(it) }
            .reduce { acc, i -> acc * i }
    }

    private fun computeDistanceMap(nodes: List<Point3D>, circuits: List<Set<Point3D>>, maxConnections: Int): TreeMap<Long, Pair<Point3D, Point3D>> {
        val distanceMap = TreeMap<Long, Pair<Point3D, Point3D>>()
        val unprocessedNodes = nodes.toMutableList()

        while (unprocessedNodes.isNotEmpty()) {
            val from = unprocessedNodes.removeFirst()
            unprocessedNodes.forEach { to ->
                val fromCircuit = circuits.first { it.contains(from) }
                if (!fromCircuit.contains(to)) {
                    val dist = from.euclideanDistanceTo(to)
                    if (distanceMap.size < maxConnections) {
                        distanceMap[dist] = Pair(from, to)
                    } else if (dist < distanceMap.lastKey()) {
                        distanceMap.remove(distanceMap.lastKey())
                        distanceMap[dist] = Pair(from, to)
                    }
                }
            }
        }
        return distanceMap
    }

    private fun mergeCircuits(
        distanceMap: TreeMap<Long, Pair<Point3D, Point3D>>,
        circuits: MutableList<MutableSet<Point3D>>,
        maxSize: Int
    ): Pair<Point3D, Point3D>? {
        distanceMap.values.forEach { (from, to) ->
            val fromCircuit = circuits.first { it.contains(from) }
            val toCircuit = circuits.first { it.contains(to) }
            if (fromCircuit != toCircuit) { //merge circuits
                fromCircuit.addAll(toCircuit)
                circuits.remove(toCircuit)

                if (fromCircuit.size >= maxSize) {
                    return Pair(from, to) //return when we have a full circuit
                }
            }
        }
        return null
    }


    fun calculate2(inputStr: String, connections: Int = 1000): Int {
        val nodes = inputStr.filledLines().map { Point3D(it) }
        val circuits = nodes.map { mutableSetOf(it) }.toMutableList()

        var finalPair: Pair<Point3D, Point3D>? = null

        while (finalPair == null) {

            val distanceMap = computeDistanceMap(nodes, circuits, connections)
            finalPair = mergeCircuits(distanceMap, circuits, nodes.size)

            circuits.sortByDescending { it.size }
        }

        return finalPair.first.x * finalPair.second.x
    }


}

