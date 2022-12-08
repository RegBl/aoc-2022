package day04

import readInput

fun main() {
    val input = readInput("\\day04\\Day04")

    val range1 = 1..9
    val range2 = 2..8

    range1.intersect(range2).let { }

    val processedInput = input.map { line ->
        line.split(",").map { half ->
            half.split("-")
        }.map { it.first().toInt()..it.last().toInt() }
    }.count { ranges ->
        (ranges.first().contains(ranges.last().first))
                || (ranges.last().contains(ranges.first().first))
    }

//    processedInput.printEach()
    println(processedInput)

    val emptyList = listOf("", "", "", "")

    println(emptyList)
}