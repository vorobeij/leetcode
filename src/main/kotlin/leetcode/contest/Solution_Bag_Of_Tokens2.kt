package leetcode.contest

import java.util.*

class Solution_Bag_Of_Tokens2 {

    var P: Int = 0
    var tokens: MutableList<Int> = arrayListOf()
    var score: Int = 0

    inline fun canPlay(): Boolean {
        return canPlayFaceUp() || canPlayFaceDown()
    }

    inline fun canPlayFaceUp(): Boolean {
        return tokens.any { it <= P }
    }

    inline fun playFaceUp() {
        val token = tokens.find { it <= P } ?: error("can not play face up")
        tokens.remove(token)
        P -= token
        score++
    }

    inline fun canPlayFaceDown(): Boolean {
        return score >= 1 && tokens.size > 1
    }

    inline fun playFaceDown() {
        val token = tokens.last()
        tokens.remove(token)
        P += token
        score--
    }

    fun bagOfTokensScore(tokens: IntArray, P: Int): Int {
        Arrays.sort(tokens)
        this.P = P
        this.tokens = tokens.toMutableList()

        while (canPlay()) {
            when {
                canPlayFaceUp() -> playFaceUp()
                canPlayFaceDown() -> playFaceDown()
            }
        }
        return score
    }
}