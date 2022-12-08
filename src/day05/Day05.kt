package day05

import readInput
import splitByEmptyLine

fun main() {
    val (stacksRaw, instructionsRaw) =
        readInput("\\day05\\Day05")
            .splitByEmptyLine()

    val stacks = stacksRaw.map { line ->
        line.chunked(4)
    }.transpose().map {
        it.dropLast(1)
    }.map { stack ->
        stack.map {
            it?.trim()
        }.filterNot { it.isNullOrBlank() }
    }

    val instructions = instructionsRaw.map { line ->
        val regex = Regex("[0-9]+")
        val test = regex.findAll(line).toList()
        val testTwo = test.map { it.value.toInt() }
        testTwo
    }

    val partOne = moveSingleCrates(instructions, stacks as List<List<String>>)
        .map { it.first()[1] }.toCharArray()

    val partTwo = moveGroupedCrates(instructions, stacks)
        .map { it.first()[1] }.toCharArray()

    println(partOne)
    println(partTwo)
}

fun List<List<String>>.transpose(): List<MutableList<String?>> {
    val thisSize = this.last().size
    val retList: List<MutableList<String?>> = List(thisSize) { mutableListOf() }
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
        for (crate in 0 until instructionSet.first()) {
            retStacks[instructionSet[2] - 1].add(0, retStacks[instructionSet[1] - 1].removeFirst().toString())
        }
    }

    return retStacks
}

fun moveGroupedCrates(instructions: List<List<Int>>, stacks: List<List<String>>): List<List<String>> {
    val retStacks = stacks.map { it.toMutableList() }
    instructions.forEach { instructionSet ->
        val (crateQuantity, fromStack, toStack) = instructionSet
        val substack = retStacks[fromStack - 1].take(crateQuantity)
        retStacks[toStack - 1].addAll(0, substack)
        for (crate in 0 until crateQuantity) retStacks[fromStack - 1].removeFirst()
    }

    return retStacks
}
