package sber

fun main() {
    // AAAABBBCDDAAA -> 4A3BC2D3A
    println(convert("AAAABBBCDDAAA") == "4A3BC2D3A")
    println(convert("") == "")
    println(convert("aa") == "2a")
    println(convert("ab") == "ab")
    println(convert("a") == "a")
}

fun convert(s: String): String {

    var letterCounter = 1
    val sb = StringBuilder()
    for (i in 0 until s.length - 1) {

        if (s[i] == s[i + 1]) {
            letterCounter++
        } else {

            sb.appendLetters(letterCounter, s[i])
            letterCounter = 1
        }
    }
    if (s.isNotEmpty()) {
        sb.appendLetters(letterCounter, s.last())
    }

    return sb.toString()
}

private fun StringBuilder.appendLetters(letterCounter: Int, letter: Char) {
    append("${if (letterCounter > 1) letterCounter else ""}${letter}")
}