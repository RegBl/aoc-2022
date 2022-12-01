package day01

import org.junit.jupiter.api.Test
import readInput

class Day01KtTest {
    private val testInput = readInput("\\day01\\Day01_test")
    private val input = readInput("\\day01\\Day01")

    @Test
    fun `test input not null`() {
        assert(
            testInput.isNotEmpty()
        )
    }

    @Test
    fun `input not null`() {
        assert(input.isNotEmpty())
    }

    @Test
    fun `input splits into multiple objects`() {
        val elvesWithCalories = input.groupBy {
            it.isEmpty()
        }.values
        assert(elvesWithCalories.size > 1)
    }
}