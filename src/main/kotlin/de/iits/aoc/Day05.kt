package de.iits.aoc

import filledLines
import join
import stripped

class Day05 {

    private lateinit var listOfList: List<MutableList<String>>

    fun calculate(inputStr: String, movement: String, inOrder: Boolean): String {
        val lines = inputStr.filledLines()
        initList(lines.last())
        lines.dropLast(1).forEach { fillArrays(it, listOfList.size) }

        movement.filledLines().forEach { parseMove(it, inOrder) }

        return listOfList.joinToString("") { it.first() }
    }


    fun calculate2(inputStr: String) =
        inputStr.trim().lines()

    private fun fillArrays(line: String, size: Int) {
        val list = line.toList().chunked(4)
        repeat(size) {
            if (list[it].join().stripped().isNotEmpty()) {
                listOfList[it].add(list[it].join().stripped())
            }
        }
    }

    private fun initList(numbers: String) {
        val size = numbers.toList().chunked(4)
            .last().join().stripped().toInt()
        listOfList = List(size) { mutableListOf() }
    }

    private fun parseMove(input: String, inOrder: Boolean) {
        val instr = input.split(" ")

        when (inOrder) {
            true -> move2(instr[1].toInt(), listOfList[instr[3].toInt() - 1], listOfList[instr[5].toInt() - 1])
            false -> move(instr[1].toInt(), listOfList[instr[3].toInt() - 1], listOfList[instr[5].toInt() - 1])
        }

    }

    private fun move(count: Int, source: MutableList<String>, target: MutableList<String>) {
        repeat(count) {
            target.add(0, source.first())
            source.removeFirst()
        }
    }

    private fun move2(count: Int, source: MutableList<String>, target: MutableList<String>) {
        target.addAll(0, source.take(count))
        repeat(count) {
            source.removeFirst()
        }
    }


}

