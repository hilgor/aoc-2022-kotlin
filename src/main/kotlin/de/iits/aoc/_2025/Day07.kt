package de.iits.aoc._2025

import de.iits.aoc.util.filledLines

class Day07 {

    fun calculate(inputStr: String) = inputStr.filledLines()
        .let { field ->
            val beams = mutableSetOf(field.first().indexOf('S'))

            field.drop(1)
                .fold(0) { splitCount, row ->
                    splitCount + splitOnNextRow(row, beams)
                }
        }

    private fun splitOnNextRow(row: String, beams: MutableSet<Int>): Int =
        beams.fold(0 to mutableSetOf<Int>()) { (splits, newBeams), beam ->
            if (row[beam] == '^') {
                newBeams.add(beam - 1)
                newBeams.add(beam + 1)
                splits + 1 to newBeams
            } else {
                newBeams.add(beam)
                splits to newBeams
            }
        }.let { (splits, newBeams) ->
            beams.clear()
            beams.addAll(newBeams)
            splits
        }


    fun calculate2(inputStr: String) = inputStr.filledLines()
        .let { field ->
            processPath(field, 1, field.first().indexOf('S'), mutableMapOf())
        }

    private fun processPath(field: List<String>, row: Int, position: Int, cache: MutableMap<Pair<Int,Int>, Long>): Long =
        when {
            cache.containsKey(row to position) -> cache[row to position]!!
            row >= field.size -> 1L
            field[row][position] == '^' -> {
                val result = processPath(field, row + 1, position - 1, cache) + processPath(field, row + 1, position + 1, cache)
                cache[row to position] =  result
                result
            }


            else -> {
                processPath(field, row + 1, position, cache)
            }
        }

}

