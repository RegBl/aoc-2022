package day02

import readInput

fun main() {
    val input = readInput("\\day02\\Day02")
    val testInput = readInput("\\day02\\Day02_test")

    val contestantChoices = inputListToContestantChoicesList(input)
    val contestantScores = contestantChoicesToContestantScores(contestantChoices)

    // Part 1
    println(contestantChoices)
    println(contestantScores)
    println(contestantScores.last().sum())

    // Part 2
}

enum class CHOICE {ROCK, PAPER, SCISSORS, DEPENDENT}
enum class STRATEGY {WIN, LOSE, DRAW, NORMAL}
data class PlayerRound(var choice: CHOICE, val strategy: STRATEGY)

fun contestantChoicesToContestantScores(choices: List<List<PlayerRound>>): List<List<Int>> {
    val firstContestantScores = mutableListOf<Int>()
    val secondContestantScores = mutableListOf<Int>()
    choices.first().zip(choices.last()) { first, last ->
        val scores = compareChoices(first, last)
        firstContestantScores.add(scores.first)
        secondContestantScores.add(scores.second)
    }
    return listOf(firstContestantScores, secondContestantScores)
}

fun compareChoices(first: PlayerRound, last: PlayerRound): Pair<Int, Int> {
    val firstChoiceNumValue = first.choice.ordinal + 1
    val lastChoiceNumValue = last.choice.ordinal + 1
    return when (last.strategy) {
        STRATEGY.NORMAL -> {
            when (Pair(first.choice, last.choice)) {
                // Tie conditions
                (Pair(CHOICE.ROCK, CHOICE.ROCK)),
                (Pair(CHOICE.SCISSORS, CHOICE.SCISSORS)),
                (Pair(CHOICE.PAPER, CHOICE.PAPER)) -> Pair((firstChoiceNumValue + 3),(lastChoiceNumValue + 3))
                // Loss/win conditions
                (Pair(CHOICE.ROCK, CHOICE.PAPER)),
                (Pair(CHOICE.PAPER, CHOICE.SCISSORS)),
                (Pair(CHOICE.SCISSORS, CHOICE.ROCK)) -> Pair(firstChoiceNumValue, (lastChoiceNumValue + 6))
                // Win/loss conditions
                (Pair(CHOICE.ROCK, CHOICE.SCISSORS)),
                (Pair(CHOICE.PAPER, CHOICE.ROCK)),
                (Pair(CHOICE.SCISSORS, CHOICE.PAPER)) -> Pair((firstChoiceNumValue + 6), lastChoiceNumValue)
                else -> throw Exception()
            }
        }
        STRATEGY.WIN -> {
            when (first.choice) {
                CHOICE.ROCK -> last.choice = CHOICE.PAPER
                CHOICE.PAPER -> last.choice = CHOICE.SCISSORS
                CHOICE.SCISSORS -> last.choice = CHOICE.ROCK
                else -> throw Exception()
            }
            Pair((first.choice.ordinal + 1), (last.choice.ordinal + 1 + 6))
        }
        STRATEGY.LOSE -> {
            when (first.choice) {
                CHOICE.ROCK -> last.choice = CHOICE.SCISSORS
                CHOICE.PAPER -> last.choice = CHOICE.ROCK
                CHOICE.SCISSORS -> last.choice = CHOICE.PAPER
                else -> throw Exception()
            }
            Pair((first.choice.ordinal + 1 + 6), (last.choice.ordinal + 1))
        }
        STRATEGY.DRAW -> {
            when (first.choice) {
                CHOICE.ROCK -> last.choice = CHOICE.ROCK
                CHOICE.PAPER -> last.choice = CHOICE.PAPER
                CHOICE.SCISSORS -> last.choice = CHOICE.SCISSORS
                else -> throw Exception()
            }
            Pair((first.choice.ordinal + 1 + 3), (last.choice.ordinal + 1 + 3))
        }
    }
}

fun translateChoice(choice: String): PlayerRound {
    return when (choice) {
        "A" -> PlayerRound(CHOICE.ROCK, STRATEGY.NORMAL)
        "B" -> PlayerRound(CHOICE.PAPER, STRATEGY.NORMAL)
        "C" -> PlayerRound(CHOICE.SCISSORS, STRATEGY.NORMAL)
        "X" -> PlayerRound(CHOICE.DEPENDENT, STRATEGY.LOSE)
        "Y" -> PlayerRound(CHOICE.DEPENDENT, STRATEGY.DRAW)
        "Z" -> PlayerRound(CHOICE.DEPENDENT, STRATEGY.WIN)
        else -> throw Exception("Unknown input")
    }
}

fun inputListToContestantChoicesList(input: List<String>): List<List<PlayerRound>> {
    val firstContestantChoices = mutableListOf<PlayerRound>()
    val secondContestantChoices = mutableListOf<PlayerRound>()
    input.forEach {round ->
        val test = round.split(" ")
        firstContestantChoices.add(translateChoice(test.first()))
        secondContestantChoices.add(translateChoice(test.last()))
    }
    return listOf(firstContestantChoices, secondContestantChoices)
}