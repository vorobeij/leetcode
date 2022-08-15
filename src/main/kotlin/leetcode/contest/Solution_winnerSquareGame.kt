package leetcode.contest

class Solution_winnerSquareGame {

    val losingSet = mutableSetOf<Int>()

    fun winnerSquareGame(n: Int): Boolean {
        for (i in 1 until n) {
            canWin(i)
        }
        return canWin(n)
    }

    fun canWin(n: Int): Boolean {
        if (isSquare(n)) return true
        for (square in 1..Math.sqrt(n.toDouble()).toInt()) {
            if (losingSet.contains(n - square * square)) return true
        }
        losingSet.add(n)
        return false
    }

    fun isSquare(n: Int): Boolean {
        var t = 1
        while (t * t <= n) {
            if (n == t * t) return true
            t++
        }
        return false
    }
}
