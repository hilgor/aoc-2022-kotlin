fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")


    val matrix = List(4) { listOf('1','2','3','4', '5').shuffled() }

    matrix.forEach { println(it) }
    println("------------ orig")
    rotate90(transpose(matrix)).forEach { println(it) }
    println("------------ transpose")





}