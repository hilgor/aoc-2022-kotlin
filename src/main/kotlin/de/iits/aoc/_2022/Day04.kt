package de.iits.aoc._2022

import sparseListOf

class Day04 {

    fun calculate(inputStr: String) =
        inputStr.trim().lines()
            .map { with(it.split(",")) { compareContent(first(), last()) } }
            .count { it }

    fun calculate2(inputStr: String) =
        inputStr.trim().lines()
            .map { with(it.split(",")) { compareContent2(first(), last()) } }
            .count { it }




    fun compareContent(first: String, second: String) =
        toRange(first).containsAll(toRange(second))
                || toRange(second).containsAll(toRange(first))

    fun toRange(range: String) =
        with(range.split("-")) {
            sparseListOf(first().toInt() .. last().toInt())
        }


    fun compareContent2(first: String, second: String) =
        toRange(first).intersect(toRange(second).toSet()).isNotEmpty()

}

