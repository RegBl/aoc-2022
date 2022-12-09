package day06

import readInput

fun main() {
    val testInput = readInput("\\day06\\Day06_test")[3]
    val input = readInput("\\day06\\Day06").first()

    val partOne = input.indexOf(input.firstNonRepeatingSetOfFour()) + 4
    val partTwo = input.indexOf(input.firstNonRepeatingSetOfFourteen()) + 14

    println(partOne)
    println(partTwo)
}

fun String.firstNonRepeatingSetOfFour(): String = this.windowed(size = 4, step = 1).first { it.toSet().size == 4}

fun String.firstNonRepeatingSetOfFourteen(): String = this.windowed(size = 14, step = 1).first { it.toSet().size == 14}