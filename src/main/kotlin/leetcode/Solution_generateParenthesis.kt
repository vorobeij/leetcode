package leetcode

class Solution_generateParenthesis {

    val open = '('
    val close = ')'

    fun generateParenthesis(n: Int): List<String> {
        if (n == 0) return emptyList()

        // create first correct sequence
        var sequences_1 = mutableSetOf("()")
        var k = 1

        while (k < n) {
            val correctSequences = mutableSetOf<String>()
            for (s in sequences_1) {
                val seq = "(" + s
                for (i in seq.indices) {
                    val correctSeq = seq.replaceRange(i, i, ")")
                    if (isCorrectSequence(correctSeq)) {
                        correctSequences.add(correctSeq)
                    }
                }
            }
            sequences_1 = correctSequences
            k++
        }
        return sequences_1.toList()
    }

    fun isCorrectSequence(seq: String): Boolean {
        var openings = 0
        var closings = 0

        var i = 0
        while (i < seq.length && closings <= openings) {
            if (seq[i] == open) openings++
            if (seq[i] == close) closings++
            i++
        }
        return closings == openings
    }
}