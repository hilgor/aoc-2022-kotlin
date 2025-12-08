package de.iits.aoc._2025

import de.iits.aoc.util.toLongRange

class Day02 {

    fun calculate(inputStr: String) = inputStr.split(",")
        .flatMap() { it.toLongRange() }
        .filter { isInvalid(it) }
        .sum()

    private fun isInvalid(id: Long): Boolean {
        val strId = id.toString()
        if (strId.length % 2 == 1) return false

        val prefix = strId.substring(0, strId.length / 2)
        val suffix = strId.substring(strId.length / 2)
        if (prefix == suffix) {
//            println(id)
            return true
        }
        return false
    }

    private fun isInvalid2(id: Long): Boolean {
        val strId = id.toString()

        for (i in 1 until strId.length / 2 + 1) {
            val prefix = strId.substring(0, i)
            val pattern = prefix.repeat(strId.length / prefix.length)
            if (pattern == strId) {
//                println(id)
                return true
            }
        }
        return false
    }

    fun calculate2(inputStr: String) = inputStr.split(",")
    .flatMap() { it.toLongRange() }
    .filter { isInvalid2(it) }
    .sum()

}

