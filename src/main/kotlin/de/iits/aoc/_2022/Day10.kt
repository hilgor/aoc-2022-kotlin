package de.iits.aoc._2022

import de.iits.aoc.util.filledLines
import de.iits.aoc.util.getAllInts

class Day10 {

    private val regValues = mutableListOf(1)
    private val screen = List(6) { CharArray(40) }

    fun calculate(inputStr: String): Int {
        inputStr.filledLines().forEach { parseLine(it) }
//        println("20: ${regValues[19]}, 60: ${regValues[59]}, 100: ${regValues[99]}, 140: ${regValues[139]}, 180: ${regValues[179]}, 220: ${regValues[219]}")

        return (20 * regValues[19])
            .plus(60 * regValues[59])
            .plus(100 * regValues[99])
            .plus(140 * regValues[139])
            .plus(180 * regValues[179])
            .plus(220 * regValues[219])
    }


    fun calculate2(inputStr: String) : List<CharArray>{
        inputStr.filledLines().forEach { parseLine(it) }
        screen.forEach { println(it) }

        return screen
    }


    private fun parseLine(input: String) {
        setPixelValue()
        regValues.add(regValues.last())
        val value = input.getAllInts(" ")
        if (value.isNotEmpty()) {
            setPixelValue()
            regValues.add(regValues.last() + value.first())
        }
    }

    private fun setPixelValue() {
        val position = determinePosition()
        val allowedPixels = listOf(regValues.last()-1, regValues.last(), regValues.last()+1)
        screen[position.second][position.first] = when (allowedPixels.contains(position.first)) {
            true -> '#'
            false -> '.'
        }
    }

    private fun determinePosition(): Pair<Int, Int> {
        val x = (regValues.size -1).mod(40)
        val y = (regValues.size -1).div(40)
//        println("Number: ${regValues.size}, position: ${Pair(x,y)}")
        return Pair(x,y)
    }

}

