package de.iits.aoc._2022

import filledLines
import getAllInts
import java.math.BigInteger

class Day11 {

    fun calculate(inputStr: String, numberRounds: Int, worryDec: Int, reducer: Int) : Long{
        val monkeys = inputStr.trim().split("\n\n")
            .map { createMonkey(it, worryDec, reducer) }
        repeat(numberRounds) {count ->
            monkeys.forEach { monkey ->
                monkey.makeRound().forEach {
                    monkeys[it.first].addItem(it.second)
                }
            }
//            if (count.mod(100)==0) println("Count: $count")
//            log(count, monkeys)
        }

        return monkeys
            .map { it.counter }
            .sortedDescending()
            .take(2)
            .reduce { acc, i -> acc.times(i) }
    }

    private fun log(count: Int, monkeys: List<Monkey>) {
        println("------")
        println("Round ${count + 1} ends!")
        monkeys.forEach { it.printState() }
    }

    private fun createMonkey(input: String, worryDec: Int = 3, reducer: Int): Monkey {
        val lines = input.lines()
        val number = lines[0].getAllInts().first()
        val items = lines[1].split(": ").last()
            .getAllInts(",")
            .map { BigInteger.valueOf(it.toLong()) }
            .toMutableList()
        val opStr = lines[2].trim().removePrefix("Operation: new = old ").split(" ")
        val operation = createOp(opStr.first(), opStr.last())
        val test = createTest(
            lines[3].getAllInts().first(),
            lines[4].getAllInts().first(), lines[5].getAllInts().first()
        )

        return Monkey(items, operation, test, number, worryDec, reducer)
    }

    private fun createOp(operand: String, delta: String): (BigInteger) -> BigInteger =
        { worry: BigInteger ->
            when (operand) {
                "+" -> worry + getDelta(delta, worry)
                "*" -> worry * getDelta(delta, worry)
                else -> BigInteger.valueOf(-1L)
            }
        }

    private fun getDelta(delta: String, worry: BigInteger) =
        when (delta) {
            "old" -> worry
            else -> BigInteger.valueOf(delta.toLong())
        }

    private fun createTest(div: Int, trueTar: Int, falseTar: Int): (BigInteger) -> Int =
        { test: BigInteger ->
            when (test.mod(BigInteger.valueOf(div.toLong()))) {
                BigInteger.ZERO -> trueTar
                else -> falseTar
            }
        }


    fun calculate2(inputStr: String) =
        inputStr.filledLines()


}


private data class Monkey(
    val items: MutableList<BigInteger>,
    val operation: (old: BigInteger) -> BigInteger,
    val test: (worry: BigInteger) -> Int,
    val number: Int,
    val worryDecrease: Int = 3,
    val reducer: Int ,
//    = BigInteger.valueOf(23L * 19L * 13L * 17L),
    var counter: Long = 0L
) {
    fun makeRound(): List<Pair<Int, BigInteger>> {
        val result = mutableListOf<Pair<Int, BigInteger>>()
        repeat(items.size) {
            val newWorry = operation(items.first())
                .div(BigInteger.valueOf(worryDecrease.toLong()))
                .remainder(BigInteger.valueOf(reducer.toLong()))
            items.removeFirst()
            result.add(Pair(test(newWorry), newWorry))
            counter++
        }
        return result
    }

    fun addItem(item: BigInteger) {
        items.add(item)
    }

    fun printState() =
        println("Monkey $number: $items")

//    fun reduceWorry(worry: BigInteger) :BigInteger {
//        val newWorry = if (worry.mod(reducer) == BigInteger.ZERO) {
//             worry.div(reducer)
//        } else worry
//        return if (worryDecrease != 1)
//            newWorry.div(BigInteger.valueOf(worryDecrease.toLong()))
//        else newWorry
////        19, 5, 11, 17, 7, 13, 3, 2
//    }

}
