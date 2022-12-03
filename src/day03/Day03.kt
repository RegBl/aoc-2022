package day03

import readInput

fun main() {
    val input = readInput("\\day03\\Day03")
    readInput("\\day03\\Day03_test")
    val scores = ('a'..'z').zip(1..26).toMap() +
            ('A'..'Z').zip(27..52).toMap()

    val commonLetters = input.map { line ->
        line.take(line.length / 2).first { line.takeLast(line.length / 2).contains(it) }
    }.map { scores[it] ?: 0 }

    val groupsOfThree = input.chunked(3)
        .map { strings ->
            strings[0].first { strings[1].contains(it) && strings[2].contains(it) }
        }.map { scores[it] ?: 0 }

    // Part 1
    println(commonLetters.sum())

    // Part 2
    println(groupsOfThree.sum())
}