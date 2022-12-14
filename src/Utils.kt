import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt")
    .readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

fun <T> Collection<T>.printEach() = this.forEach(::println)

fun List<String>.splitByEmptyLine(): List<List<String>> {
    return fold(mutableListOf(mutableListOf<String>())) { acc, line ->
        if (line.isEmpty()) {
            acc.add(mutableListOf<String>())
        } else {
            acc.last().add(line)
        }
        acc
    }
}