package de.iits.aoc._2022

import filledLines
import java.util.regex.Pattern
import kotlin.math.ceil

class Day19(inputStr: String) {

    private val bpPattern =
        Pattern.compile("Blueprint (\\d+): Each ore robot costs (\\d+) ore; Each clay robot costs (\\d+) ore; Each obsidian robot costs (\\d+) ore and (\\d+) clay; Each geode robot costs (\\d+) ore and (\\d+) obsidian;")

    private val blueprints = inputStr.filledLines()
        .map { it.replace(".", ";") }
        .map { parseBlueprint(it) }

    fun calculate(): Int {

        var sum = 0
        for (blueprint in blueprints) {
            val result = blueprint.id * simulateRound2(blueprint, 24)
            println(result)
            sum += result
        }

        return sum
    }

    private fun simulateRound2(blueprint: Blueprint, time: Int): Int {

        val startState = State(blueprint, time)

        val queue = ArrayDeque<State>()
        var max = 0

        queue.addLast(startState)

        while (queue.isNotEmpty()) {
            val current = queue.removeLast()
            val timeLeft = current.timeLeft

            if (current.cannotProduceMore(max, timeLeft)) {
                continue
            }

            if (timeLeft > 0) {

                State.Bot.values()
                    .filter { current.canBuild(it) }
                    .mapNotNull { current.buildBot(it) }
                    .forEach {
                        queue.addLast(it)
                        max = maxOf(max, it.geode)
                    }
            }
        }
        return max
    }


    fun calculate2(): Int {

        var sum = 1
        for (blueprint in blueprints.take(3)) {
            val result = simulateRound2(blueprint, 32)
            println(result)
            sum *= result
        }

        return sum
    }


    private fun parseBlueprint(input: String): Blueprint {
        val m = bpPattern.matcher(input)
        m.find()
        return Blueprint(
            m.group(1).toInt(),
            m.group(2).toInt(),
            m.group(3).toInt(),
            m.group(4).toInt(),
            m.group(5).toInt(),
            m.group(6).toInt(),
            m.group(7).toInt()
        )
    }

}

private data class Blueprint(
    val id: Int,
    val oreCost: Int,
    val clayOreCost: Int,
    val obsOreCost: Int,
    val obsClayCost: Int,
    val geodeOreCost: Int,
    val geodeObsCost: Int,
) {
    val oreMax: Int = maxOf(obsOreCost, clayOreCost, geodeOreCost) + 2
    val clayMax: Int = obsClayCost + 2
    val obsMax: Int = geodeObsCost + 2
    val oreBotMax: Int = maxOf(obsOreCost, clayOreCost, geodeOreCost)
    val clayBotMax: Int = obsClayCost
    val obsBotMax: Int = geodeObsCost
}


private data class State(
    val bp: Blueprint,
    var timeLeft: Int,
    var ore: Int = 0,
    var clay: Int = 0,
    var obsidian: Int = 0,
    var geode: Int = 0,
    var oreBots: Int = 1,
    var clayBots: Int = 0,
    var obsBots: Int = 0,
) {
    enum class Bot {
        ORE, CLAY, OBSIDIAN, GEODE
    }

    fun canBuild(type: Bot): Boolean =
        when (type) {
            Bot.ORE -> oreBots < bp.oreBotMax && ore < bp.oreMax

            Bot.CLAY -> clayBots < bp.clayBotMax && clay < bp.clayMax

            Bot.OBSIDIAN -> clayBots > 0 && obsBots < bp.obsBotMax && obsidian < bp.obsMax

            Bot.GEODE -> obsBots > 0
        }

    private fun roundsUntilCanBuild(type: Bot): Int =
        when (type) {
            Bot.ORE -> if (bp.oreCost <= ore) 0 else ceil((bp.oreCost - ore) / oreBots.toFloat()).toInt()

            Bot.CLAY -> if (bp.clayOreCost <= ore) 0 else ceil((bp.clayOreCost - ore) / oreBots.toFloat()).toInt()

            Bot.OBSIDIAN -> maxOf(
                if (bp.obsOreCost <= ore) 0 else ceil((bp.obsOreCost - ore) / oreBots.toFloat()).toInt(),
                if (bp.obsClayCost <= clay) 0 else ceil((bp.obsClayCost - clay) / clayBots.toFloat()).toInt()
            )

            Bot.GEODE -> maxOf(
                if (bp.geodeOreCost <= ore) 0 else ceil((bp.geodeOreCost - ore) / oreBots.toFloat()).toInt(),
                if (bp.geodeObsCost <= obsidian) 0 else ceil((bp.geodeObsCost - obsidian) / obsBots.toFloat()).toInt()
            )
        } + 1 //factory build speed

    fun cannotProduceMore(max: Int, timeLeft: Int): Boolean {
        val futureProduction = (1..timeLeft).sumOf { it }
        return geode + futureProduction <= max
    }

    fun buildBot(type: Bot): State? {
        val timeRequired = roundsUntilCanBuild(type)

        if (timeLeft - timeRequired <= 0) return null

        val scheduled = this.copy(timeLeft = timeLeft - timeRequired)
        when (type) {
            Bot.ORE -> {
                scheduled.ore = ore - bp.oreCost + timeRequired * oreBots
                scheduled.clay += timeRequired * clayBots
                scheduled.obsidian += timeRequired * obsBots
                scheduled.oreBots++
            }

            Bot.CLAY -> {
                scheduled.ore = ore - bp.clayOreCost + timeRequired * oreBots
                scheduled.clay += timeRequired * clayBots
                scheduled.obsidian += timeRequired * obsBots
                scheduled.clayBots++
            }

            Bot.OBSIDIAN -> {
                scheduled.ore = ore - bp.obsOreCost + timeRequired * oreBots
                scheduled.clay = clay - bp.obsClayCost + timeRequired * clayBots
                scheduled.obsidian += timeRequired * obsBots
                scheduled.obsBots++
            }

            Bot.GEODE -> {
                scheduled.ore = ore - bp.geodeOreCost + timeRequired * oreBots
                scheduled.clay += timeRequired * clayBots
                scheduled.obsidian = obsidian - bp.geodeObsCost + timeRequired * obsBots
                scheduled.geode += scheduled.timeLeft
            }
        }
        return scheduled
    }

}

