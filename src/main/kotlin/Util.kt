fun sparseListOf(vararg ranges: CharRange): List<Char> = ranges.flatMap { it }

fun sparseListOf(vararg ranges: IntRange): List<Int> = ranges.flatMap { it }

fun String.stripped() = removeRegex("[^0-9a-zA-Z]")

fun List<Char>.join() = joinToString("")

fun String.filledLines() = lines().run { subList(1, this.size-1) }

fun List<Any>.dropFirst(count: Int) = subList(count, this.size)

fun String.toRange(range: String, delimiter: String) =
    with(range.split(delimiter)) {
        sparseListOf(first().toInt() .. last().toInt())
    }

fun String.removeRegex(regex: String) = replace(regex.toRegex(), "")

fun String.getAllInts(separator: String = " ") =
    split(separator).map { it.removeRegex("[^0-9]").toInt() }

fun rotate90(matrix: List<List<Char>>): List<MutableList<Char>> {
    val dest = transpose(matrix)
    dest.forEach { it.reverse() }
    return dest
}

fun rotate180(matrix: List<List<Char>>) = rotate90(rotate90(matrix))
fun rotate270(matrix: List<List<Char>>) = rotate90(rotate90(rotate90(matrix)))

fun transpose(matrix: List<List<Char>>): List<MutableList<Char>> {
    val dest = List(matrix.maxOf { it.size }) { MutableList(matrix.size) { '0' } }
    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            dest[j][i]=matrix[i][j]
        }
    }
    return dest
}
