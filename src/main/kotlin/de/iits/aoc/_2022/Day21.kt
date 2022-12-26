package de.iits.aoc._2022

import filledLines

class Day21(inputStr: String) {

    private val monkeys = inputStr
        .filledLines()
        .map { parseLine(it) }
        .associateBy { it.name }

    fun calculate(): Long {
        val tree = addNode("root", null)
        return tree.computeValue()
    }

    private fun addNode(name: String, parent: Node<MathMonkey>?): Node<MathMonkey> {
        val monkey = monkeys[name]!!
        val node = Node(monkey, parent)
        if (!monkey.hasNumber()) {
            node.children.add(addNode(monkey.firstName, node))
            node.children.add(addNode(monkey.secondName, node))
        }
        return node
    }

    private fun parseLine(input: String): MathMonkey {
        val name = input.split(": ")
        val monkey = MathMonkey(name.first())

        with(name.last().split(" ")) {
            if (this.size == 1) {
                monkey.number = this.first().toLong()
            } else {
                monkey.firstName = this[0]
                monkey.operation = this[1]
                monkey.secondName = this[2]
            }
        }
        return monkey
    }

    fun calculate2(): Long {
        val tree = addNode("root", null)
        val human = monkeys["humn"]!!
        human.number = 300.toLong()
        tree.computeValue()

        val pathToHuman = tree.pathToHumn().map { it.name }

        val start: Node<MathMonkey>
        val source: Node<MathMonkey>
        if (pathToHuman.contains(tree.children.first().value.name)) {
            start = tree.children.first()
            source = tree.children.last()
        } else {
            source = tree.children.first()
            start = tree.children.last()
        }
//        println("monkey: ${source.value.name} with value ${source.value.number}")

//        println(pathToHuman)
        start.value.number = source.value.number
        start.computeDown(pathToHuman)


        return human.number
    }
}

private data class MathMonkey(
    val name: String
) {
    var number = Integer.MIN_VALUE.toLong()
    var firstName = ""
    var secondName: String = ""
    var operation = ""
    var first = Integer.MIN_VALUE.toLong()
    var second = Integer.MIN_VALUE.toLong()

    fun hasNumber() = number > Integer.MIN_VALUE

    fun apply(): MathMonkey {
//        if (first == Integer.MIN_VALUE.toLong() || second == Integer.MIN_VALUE.toLong()) throw IllegalStateException("Numbers not yet known for monkey $name")
        number = when (operation) {
            "+" -> first + second
            "-" -> first - second
            "*" -> first * second
            "/" -> {
//                if (first % second != 0L) println("Division for Monkey $name: $first / $second")
                first / second
            }

            else -> throw IllegalStateException("Unknown Operation for monkey $name")
        }
        return this
    }

    fun computeFirst() {
        when (operation) {
            "+" -> first = number - second
            "-" -> first = number + second // x - y = z | x = z + y
            "*" -> first = number / second
            "/" -> first = number * second // x / y = z | x = z * y
        }
    }

    fun computeSecond() {
        when (operation) {
            "+" -> second = number - first
            "-" -> second = first - number // x - y = z | y = x - z
            "*" -> second = number / first
            "/" -> second = first / number // x / y = z | y = x / Z
        }
    }
}

private fun Node<MathMonkey>.computeValue(): Long =
    if (value.hasNumber() || value.name == "humn") {
        value.number
    } else {
        value.first = children.first().computeValue()
        value.second = children.last().computeValue()
        value.apply()
        value.number
    }

private fun Node<MathMonkey>.pathToHumn(): ArrayDeque<MathMonkey> {

    val stack = ArrayDeque<Node<MathMonkey>>()
    stack.addFirst(this)

    while (stack.isNotEmpty()) {
        val currentNode = stack.removeFirst()

        if (currentNode.value.name == "humn") {
            var parent: Node<MathMonkey>? = currentNode.parent
            val path = ArrayDeque<MathMonkey>()
            path.addLast(currentNode.value)

            while (parent != null) {
                path.addLast(parent.value)
                parent = parent.parent
            }
            return path
        }
        for (index in currentNode.children.size - 1 downTo 0) {
            stack.addFirst(currentNode.children[index])
        }
    }
    return ArrayDeque()
}

private fun Node<MathMonkey>.computeDown(path: Collection<String>) {
    if (path.contains(value.name)) {
        if (value.firstName.isNotEmpty()) {
            if (path.contains(children.first().value.name)) {
                value.computeFirst()
                children.first().value.number = value.first
            } else {
                value.computeSecond()
                children.last().value.number = value.second
            }
        }
        children.forEach { it.computeDown(path) }
    }
}



