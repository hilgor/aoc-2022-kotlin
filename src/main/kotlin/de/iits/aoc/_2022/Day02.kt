package de.iits.aoc._2022

class Day02 {
    private val rock = Shape(1, "rock", "paper")
    private val paper = Shape(2, "paper", "scissors")
    private val scissors = Shape(3,"scissors", "rock")

    private val itemMap = mapOf(
        "A" to rock,
        "B" to paper,
        "C" to scissors,
        "X" to rock,
        "Y" to paper,
        "Z" to scissors,
    )

    private val pointsMap = mapOf(
        "AX" to 3+0,
        "AY" to 1+3,
        "AZ" to 2+6,
        "BX" to 1+0,
        "BY" to 2+3,
        "BZ" to 3+6,
        "CX" to 2+0,
        "CY" to 3+3,
        "CZ" to 1+6,
    )

    private fun countPoints(input: String, response: String) =
        itemMap[response]!!.score + itemMap[response]!!.result(itemMap[input]!!.type)

    fun calculate(inputStr: String) =
        inputStr.lines().sumOf { with(it.split(" ")) { countPoints(first(), last()) } }

    fun calculate2(inputStr: String) =
        inputStr.lines().sumOf { with(it.split(" ")) { pointsMap[first()+last()]!! } }
}

data class Shape (val score: Int, val type: String, val opposed: String) {
    fun result(opp: String) =
        when (opp) {
            opposed -> 0
            type -> 3
            else -> 6
        }
}
