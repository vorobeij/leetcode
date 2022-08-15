package yandex

import java.io.File

object SequencesCompare {

    @JvmStatic
    fun main(args: Array<String>) {

        val f = File("input.txt")
        val line = f.readLines().first()
        val lines = line.split(" ").map { it.trim() }.filter { it.isNotBlank() }
        val seq1 = lines[0]
        val seq2 = lines[1]
        File("output.txt").writeText(compare(seq1, seq2).toString())
    }

    fun compare(s1: String, s2: String): Boolean {

        return withBackspaces(s1) == withBackspaces(s2)
    }

    fun withBackspaces(s: String): String {
        var res = s
        val regexDeleteSymbol = Regex("[a-z]\\.")
        while (res.count { it == '.' } > 0) {
            res = res
                .trimStart { it == '.' }
                .replace(regexDeleteSymbol, "")
                .trimStart { it == '.' }
            if (res.none { it != '.' }) res = ""
        }
        return res
    }
}