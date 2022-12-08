package day05

import readInput
import splitByEmptyLine

fun main() {
    val (stacksRaw, instructionsRaw) =
        readInput("\\day05\\Day05")
            .splitByEmptyLine()

    val stacks = stacksRaw.map { it.chunked(4) }
        .transpose()
        .map { it.dropLast(1) }
        .map { stack ->
            stack.map { it.trim() }.filter { it.isNotBlank() }
        }

    val instructions = instructionsRaw.map { line ->
        val regex = Regex("[0-9]+")
        val test = regex.findAll(line).toList()
        val testTwo = test.map { it.value.toInt() }
        testTwo
    }

    val partOne = moveSingleCrates(instructions, stacks)
        .map { it.first()[1] }.toCharArray()

    val partTwo = moveGroupedCrates(instructions, stacks)
        .map { it.first()[1] }.toCharArray()

    println(partOne)
    println(partTwo)
}

fun List<List<String>>.transpose(): List<MutableList<String>> {
    val thisSize = this.last().size
    val retList = List<MutableList<String>>(thisSize) { mutableListOf() }
    retList.forEachIndexed { index, stack ->
        // each stack gets crate that matches index from each layer
        this.forEach { layer ->
            if (layer.size <= index) layer + "" else stack.add(layer[index])
        }
    }
    return retList
}

fun moveSingleCrates(instructions: List<List<Int>>, stacks: List<List<String>>): List<List<String>> {
    val retStacks = stacks.map { it.toMutableList() }
    instructions.forEach { instructionSet ->
        val (crateQuantity, fromStack, toStack) = listOf(
            instructionSet.component1(),
            instructionSet.component2() - 1,
            instructionSet.component3() - 1
        )
        for (crate in 0 until crateQuantity) {
            retStacks[toStack].add(0, retStacks[fromStack].removeFirst().toString())
        }
    }
    return retStacks
}

fun moveGroupedCrates(instructions: List<List<Int>>, stacks: List<List<String>>): List<List<String>> {
    val retStacks = stacks.map { it.toMutableList() }
    instructions.forEach { instructionSet ->
        val (crateQuantity, fromStack, toStack) = listOf(
            instructionSet.component1(),
            instructionSet.component2() - 1,
            instructionSet.component3() - 1
        )
        val subStack = retStacks[fromStack].take(crateQuantity)
        retStacks[toStack].addAll(0, subStack)
        for (crate in 0 until crateQuantity) retStacks[fromStack].removeFirst()
    }
    return retStacks
}
