package de.iits.aoc._2022

class Day03 {

    val prioMap = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toList()
        .run { associateWith { indexOf(it) + 1 } }


    fun calculate(inputStr: String) =
        inputStr.trim().lines()
            .map { it.toList() }
            .sumOf {
                with(it.windowed(it.size / 2)) {
                    compareContent(this.first(), this.last())
                }
            }

    fun calculate2(inputStr: String) =
        inputStr.trim().lines()
            .map { it.toList() }
            .chunked(3)
            .sumOf {
                compareContent2(it[0], it[1], it[2])
            }


    fun compareContent(first: List<Char>, second: List<Char>) =
        first.intersect(second.toSet()).sumOf { prioMap.getOrDefault(it, 0) }

    fun compareContent2(first: List<Char>, second: List<Char>, third: List<Char>) =
        first.intersect(second.toSet()).intersect(third.toSet()).sumOf { prioMap[it]!! }

}

