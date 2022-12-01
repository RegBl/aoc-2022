package day01

import readInput

fun main() {
    fun part1(input: List<Int>): Int {
        return input.last()
    }

    fun part2(input: List<Int>): Int {
        return input.sum()
    }

    fun splitByEmptyLine(input: List<String>): List<List<String>> {
        return input.fold(mutableListOf(mutableListOf<String>())) { acc, line ->
            if (line.isEmpty()) {
                acc.add(mutableListOf<String>())
            } else {
                acc.last().add(line)
            }
            acc
        }
    }

    val input = readInput("\\day01\\Day01")

    // test if implementation meets criteria from the description, like:
    val elvesWithCalories = splitByEmptyLine(input).map { elf -> elf.sumOf { it.toInt() } }.sorted()
    val (_, elvesWithCaloriesTopThree) = elvesWithCalories.withIndex()
        .groupBy { it.index < elvesWithCalories.size - 3 }
        .map { it.value.map { it.value } }

    println(part1(elvesWithCalories))
    println(part2(elvesWithCaloriesTopThree))
}
