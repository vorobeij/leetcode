package leetcode.contest

class Solution_Bag_Of_Tokens {

    class Engine(tokensArray: List<Int>, var P: Int) {

        private val tokens = tokensArray.toMutableList()

        var score: Int = 0

        fun canPlay(): Boolean {
            return canPlayFaceUp() || canPlayFaceDown()
        }

        fun canPlayFaceUp(): Boolean {
            return tokens.any { it <= P }
        }

        fun playFaceUp() {
            val token = tokens.find { it <= P } ?: error("can not play face up")
            tokens.remove(token)
            P -= token
            score++
        }

        fun canPlayFaceDown(): Boolean {
            return score >= 1 && tokens.size > 1
        }

        fun playFaceDown() {
            val token = tokens.last()
            tokens.remove(token)
            P += token
            score--
        }

        override fun toString(): String {
            return "p = ${P}, score = ${score}, tokens = [${tokens.joinToString(", ")}]"
        }
    }

    fun bagOfTokensScore(tokens: IntArray, P: Int): Int {
        val e = Engine(tokens.sorted(), P)
        while (e.canPlay()) {
            log("before: $e")
            when {
                e.canPlayFaceUp() -> e.playFaceUp()
                e.canPlayFaceDown() -> e.playFaceDown()
            }
            log("after : $e\n")
        }
        return e.score
    }

    fun log(m: String) {
//        println(m)
    }
}