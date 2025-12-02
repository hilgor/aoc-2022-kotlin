import java.util.*

fun getInputForDay(day: Int, example: Boolean = true, year: Int = 2022): String {
    val path = (if (day < 10) "Day0$day" else "Day$day") + (if (example) "Example" else "Input")
    return object {}.javaClass.getResource("$year/$path")!!.readText()
}

fun sparseListOf(vararg ranges: CharRange): List<Char> = ranges.flatMap { it }

fun sparseListOf(vararg ranges: IntRange): List<Int> = ranges.flatMap { it }

fun sparseListOf(vararg ranges: LongRange): List<Long> = ranges.flatMap { it }

fun String.stripped() = removeRegex("[^0-9a-zA-Z]")

fun List<Char>.join() = joinToString("")

fun String.filledLines() = lines()
//    .run { subList(1, this.size - 1) }

fun List<Any>.dropFirst(count: Int) = subList(count, this.size)

fun String.toRange(delimiter: String = "-") =
    with(this.split(delimiter)) {
        (first().toInt()..last().toInt())
    }

fun String.toLongRange(delimiter: String = "-") : List<Long> =
    this.split("-").let {
        (it.first().toLong()..it.last().toLong()).toList()
    }

fun String.removeRegex(regex: String) = replace(regex.toRegex(), "")

fun String.getAllInts(separator: String = " ") =
    split(separator)
        .map { it.removeRegex("[^-0-9]") }
        .filterNot { it.isBlank() }
        .map { it.toInt() }

fun String.splitToInts() = toList().filter { it.isDigit() }.map { it.digitToInt() }
fun String.splitToInts(separator: String) = split(separator).map { it.toInt() }

fun <T : Any> rotate90(matrix: List<List<T>>, initializer: T): List<MutableList<T>> {
    val dest = transpose(matrix, initializer)
    dest.forEach { it.reverse() }
    return dest
}

fun rotate180(matrix: List<List<Int>>) = rotate90(rotate90(matrix, 0), 0)
fun rotate270(matrix: List<List<Int>>) = rotate90(rotate90(rotate90(matrix, 0), 0), 0)

fun <T : Any> transpose(matrix: List<List<T>>, initializer: T): List<MutableList<T>> {
    val dest = List(matrix.maxOf { it.size }) { MutableList(matrix.size) { initializer } }
    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            dest[j][i] = matrix[i][j]
        }
    }
    return dest
}

fun <T> List<T>.permutations(): MutableList<List<T>> {
    val solutions = mutableListOf<List<T>>()
    permutationsRecursive(this, 0, solutions)
    return solutions
}

fun <T> permutationsRecursive(input: List<T>, index: Int, answers: MutableList<List<T>>) {
    if (index == input.lastIndex) answers.add(input.toList())
    for (i in index..input.lastIndex) {
        Collections.swap(input, index, i)
        permutationsRecursive(input, index + 1, answers)
        Collections.swap(input, i, index)
    }
}

fun <T> toRealList(ints: List<Int>, objects: List<T>) =
    ints.map { objects[it] }

fun <T> List<T>.nth(n: Int): T = this[n % size]
