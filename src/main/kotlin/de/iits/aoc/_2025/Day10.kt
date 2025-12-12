package de.iits.aoc._2025

import de.iits.aoc._2025.Day10.Machine.Companion.toMachine
import de.iits.aoc.util.filledLines
import de.iits.aoc.util.splitToInts
import kotlin.math.min

class Day10 {

    fun calculate(inputStr: String) = inputStr.filledLines()
//            .take(1)
        .map { it.toMachine() }
        .map {
            it.initialize()
//            .also { println("Optimal solution: $it with ${it.sum()} presses") }
        }
        .sumOf { it.sum() }

    fun calculate1(inputStr: String) = inputStr.filledLines()
        .map { it.toMachine() }
        .map { validCombinationsFor(it.buttons, it.lightsFinalBoolean()) }
        .sumOf { it.minOf { buttons -> buttons.size } }


    private fun validCombinationsFor(buttons: List<List<Int>>, target: List<Boolean>): List<List<List<Int>>> {
        val relevantButtons = mutableListOf<List<List<Int>>>()

        val n = buttons.size
        for (mask in 1 until (1 shl n)) {
            val combination = mutableListOf<List<Int>>()
            for (i in 0 until n) {
                if ((mask and (1 shl i)) != 0) {
                    combination.add(buttons[i])
                }
            }
            if (pressButtons(combination, target.size) == target) {
                relevantButtons.add(combination)
            }
        }

        return relevantButtons
    }

    private fun pressButtons(buttons: List<List<Int>>, numLights: Int): List<Boolean> {
        val state = MutableList(numLights) { false }
        buttons.forEach { button ->
            button.forEach { idx ->
                state[idx] = !state[idx]
            }
        }
        return state
    }


    fun calculate2(inputStr: String) = inputStr.filledLines()
//        .drop(2)
        .map { it.toMachine() }
        .sumOf { machine ->
            val coeffs = buildCoeffVectors(machine.buttons, machine.lights.length)
            val patternCosts = buildPatternCosts(coeffs)
            solveJoltages(machine.joltages, patternCosts, mutableMapOf())
//                .also { println("Result for machine: $it") }
        }


    private fun buildCoeffVectors(buttons: List<List<Int>>, numLights: Int): List<List<Int>> {
        // coeffs[j][i] = 1, wenn Button j Licht i toggelt, sonst 0
        return buttons.map { button ->
            MutableList(numLights) { idx -> if (idx in button) 1 else 0 }
        }
    }

    private fun buildPatternCosts(coeffs: List<List<Int>>): Map<List<Int>, Int> {
        val minButtonsForLightPattern = mutableMapOf<List<Int>, Int>()
        val numButtons = coeffs.size
        val numLights = coeffs.firstOrNull()?.size ?: 0

        // alle Teilmengen der Buttons inkl. leere Menge
        for (mask in 0 until (1 shl numButtons)) {
            val lightPattern = MutableList(numLights) { 0 }
            var minButtons = 0

            for (b in 0 until numButtons) {
                if ((mask and (1 shl b)) != 0) {
                    minButtons++
                    val coeff = coeffs[b]
                    for (i in 0 until numLights) {
                        lightPattern[i] += coeff[i]
                    }
                }
            }

            val existing = minButtonsForLightPattern[lightPattern]
            if (existing == null || minButtons < existing) {
                minButtonsForLightPattern[lightPattern.toList()] = minButtons
            }
        }
        return minButtonsForLightPattern
//            .also { minButtonsForLightPattern.forEach { (pattern, cost) -> println("Pattern: $pattern Cost: $cost") } }
    }

    private fun solveJoltages(
        joltages: List<Int>,
        patternCosts: Map<List<Int>, Int>,
        cache: MutableMap<List<Int>, Int>
    ): Int {
        val infinity = 1_000_000

        if (joltages.all { it == 0 }) return 0
        if (joltages.any { it < 0 }) return infinity
        cache[joltages]?.let { return it }

        var best = infinity
        for ((pattern, cost) in patternCosts) {
            // Bedingung: pattern[i] <= goal[i] und gleiche Parität
            var ok = true
            for (i in joltages.indices) {
                val p = pattern[i]
                val g = joltages[i]
                if (p > g || (p % 2) != (g % 2)) {
                    ok = false
                    break
                }
            }
            if (!ok) continue

            // newGoal = (goal - pattern) / 2
            val newJoltages = List(joltages.size) { i -> (joltages[i] - pattern[i]) / 2 }
            val sub = solveJoltages(newJoltages, patternCosts, cache)
            best = min(best, cost + 2 * sub)
//            if (sub < infinity) {
//            }
        }

        cache[joltages] = best
        return best
    }

    private data class Machine(val lights: String, val buttons: List<List<Int>>, val joltages: List<Int> = listOf()) {

        companion object {
            fun String.toMachine(): Machine {
                val ixLightsEnd = this.indexOf("]")
                val ixButtonsEnd = this.indexOf("{")

                val lights = this.substring(1, ixLightsEnd)
//            .also { println("Lights: $it") }

                val buttons = this.substring(ixLightsEnd + 2, ixButtonsEnd - 1)
//            .also { println("Buttons: $it") }
                    .split(" ").map { it.substring(1, it.length - 1) }
                    .map { it.splitToInts(",") }

                val joltages = this.substring(ixButtonsEnd + 1, this.length - 1)
                    .splitToInts(",")
//            .also { println("Joltages: $it") }

                return Machine(lights, buttons, joltages)
            }
        }

        fun lightsFinalState() = lights.toList().map { if (it == '#') 1 else 0 }
        fun lightsFinalBoolean() = lights.toList().map { it == '#' }

        fun initialize(): List<Int> {
            val numLights = lights.length
            val numButtons = buttons.size
            val currentState = MutableList(numLights) { 0 }
            val targetState = lightsFinalState()

            //// Step 1: Build matrix equation Ax = b in GF(2)
            //calculate change vector
            val changeVector = currentState.mapIndexed { index, startValue ->
                startValue + targetState[index] % 2
            }
            //build matrix
            val matrix = buildMatrix(numLights, numButtons, changeVector)

            //// Step 2: Solve matrix equation using Gaussian elimination in GF(2)
            val pivotCols = convertMatrixToREF(matrix)

            //// Step 3: Back substitution to find solution
            val firstSolution = buildFirstSolution(matrix, pivotCols)

            //// Step 4: Calculate null space vectors
            val nullSpaceVectors = calculateNullSpaceVectors(matrix, pivotCols)

            //// Step 5: Minimize solution
            val optimalSolution = calculateShortestPathToSolution(firstSolution, nullSpaceVectors)

            return optimalSolution


        } //initialise

        private fun buildMatrix(numLights: Int, numButtons: Int, changeVector: List<Int>): MutableList<MutableList<Int>> {
            val matrix = MutableList(numLights) { MutableList(numButtons) { 0 } }
            for (j in 0 until numButtons) {
                for (lightIndex in buttons[j]) {
                    matrix[lightIndex][j] = 1
                }
            }

            //add target state as last column
            for (i in 0 until numLights) {
                matrix[i].add(changeVector[i])
            }
//            matrix.forEach { println(it) }
            return matrix
        }

        private fun convertMatrixToREF(matrix: MutableList<MutableList<Int>>): MutableList<Int> {
            val numLights = matrix.size
            val numButtons = matrix[0].size - 1 // Last column is the target

            var pivotRow = 0
            val pivotCols = mutableListOf<Int>()

            for (col in 0 until numButtons) {
                if (pivotRow >= numLights) break

                // Find a row with a leading 1 in the current column
                var candidateRow = pivotRow
                while (candidateRow < numLights && matrix[candidateRow][col] == 0) {
                    candidateRow++
                }

                if (candidateRow == numLights) {
                    continue // No leading 1 found in this column
                }

                // Swap the current row with the candidate row
                if (candidateRow != pivotRow) {
                    val temp = matrix[pivotRow]
                    matrix[pivotRow] = matrix[candidateRow]
                    matrix[candidateRow] = temp
                }

                // Record the pivot column
                pivotCols.add(col)

                // Eliminate all rows below the pivot
                for (row in pivotRow + 1 until numLights) {
                    if (matrix[row][col] == 1) {
                        // Perform row operation (XOR in GF(2))
                        for (c in col until numButtons + 1) {
                            matrix[row][c] = (matrix[row][c] + matrix[pivotRow][c]) % 2
                        }
                    }
                }
                pivotRow++
            }

            // Check rows containing only zeros on the left but a 1 in the last column
            for (row in pivotRow until numLights) {
                if (matrix[row][numButtons] == 1) throw IllegalStateException("No solution exists")
            }

            return pivotCols

        } //convertMatrixToREF

        private fun buildFirstSolution(matrix: MutableList<MutableList<Int>>, pivotCols: MutableList<Int>): List<Int> {
            val numButtons = matrix[0].size - 1
            val solution = MutableList(numButtons) { 0 }

            //iterate backwards through the rows
            for (i in pivotCols.size - 1 downTo 0) {
                val pivotCol = pivotCols[i]

                var sum = matrix[i][numButtons] // Start with the last "augmented" column (b value)

                // Subtract contributions from known variables
                for (j in pivotCol + 1 until numButtons) {
                    if (matrix[i][j] == 1) {
                        // Perform row operation (XOR in GF(2))
                        sum = (sum + solution[j]) % 2
                    }
                }

                solution[pivotCol] = sum
            }
            return solution
        }

        private fun calculateNullSpaceVectors(matrix: MutableList<MutableList<Int>>, pivotCols: MutableList<Int>): List<List<Int>> {
            val numButtons = matrix[0].size - 1
            val nullBasisVectors = mutableListOf<List<Int>>()

            val freeCols = (0 until numButtons).filter { it !in pivotCols }

            for (freeCol in freeCols) {
                val basis = MutableList(numButtons) { 0 }
                basis[freeCol] = 1

                // Solve for the pivot variables to make the total effect 0
                // We perform back substitution similar to Step 3 above, but we treat the 'augmented' value as 0.
                for (i in pivotCols.size - 1 downTo 0) {
                    val pivotCol = pivotCols[i]
                    var sum = 0

                    for (j in pivotCol + 1 until numButtons) {
                        if (matrix[i][j] == 1) {
                            sum = (sum + basis[j]) % 2
                        }
                    }

                    basis[pivotCol] = sum
                }

                nullBasisVectors.add(basis)
            }

            return nullBasisVectors
        }

        private fun calculateShortestPathToSolution(firstSolution: List<Int>, nullSpaceVectors: List<List<Int>>): List<Int> {
            var minPresses = firstSolution.sum()
            var minPressesSolution = firstSolution.toList()

            val numBasis = nullSpaceVectors.size
            val totalCombinations = 1 shl numBasis // 2^numBasis

            for (combination in 0 until totalCombinations) {
                val candidateSolution = firstSolution.toMutableList()

                // Apply the selected null space vectors based on the bits in combination
                for (i in 0 until numBasis) {
                    // Check if the i-th bit is set in combination
                    if ((combination and (1 shl i)) != 0) {
                        // Add the null space vector to the candidate solution
                        for (j in candidateSolution.indices) {
                            candidateSolution[j] = (candidateSolution[j] + nullSpaceVectors[i][j]) % 2
                        }
                    }
                }

                val presses = candidateSolution.sum()
                if (presses < minPresses) {
                    minPresses = presses
                    minPressesSolution = candidateSolution.toList()
                }
            }

            return minPressesSolution
        }

    }

}






