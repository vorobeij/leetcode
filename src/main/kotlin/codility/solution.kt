package codility

object Solution {
    // you can also use imports, for example:
    // import kotlin.math.*

    // you can write to stdout for debugging purposes, e.g.
    // println("this is a debug message")

    fun solution(A: IntArray): Int {
        // write your code in Kotlin 1.3.11 (Linux)
        val N = A.size - 1
        var min = Integer.MAX_VALUE
        for (p in 1 until N) {
            for (q in p + 2 until N) {
                val cost = A[p] + A[q]
                if (cost < min) min = cost
            }
        }
        return min
    }

}


