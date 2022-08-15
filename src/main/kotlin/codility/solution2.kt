package codility.cod

object Solution2 {
    // you can also use imports, for example:
    // import kotlin.math.*

    // you can write to stdout for debugging purposes, e.g.
    // println("this is a debug message")

    fun solution(A: IntArray): Int {
        // write your code in Kotlin 1.3.11 (Linux)

        val set = hashSetOf<Int>()
        var longest = 0
        var indexFirst = 0
        var sliceSize = 0
        var i = 0

        fun updateLongest() {
            if (sliceSize > longest) longest = sliceSize
        }

        while (i < A.size) {
            set.add(A[i])
            if (set.size < 3) {
                sliceSize++
                i++
            } else {
                updateLongest()
                set.clear()
                sliceSize = 0

                if (i > 1) {
                    // Small optimisations:
                    // We know here, that [indexFirst;i-1] is bi-valued, and A[i] breaks the slice.
                    // Hence, [j;i] is a bi-valued slice:
                    val previousNumber = A[i - 1]
                    for (j in i - 2 downTo indexFirst) {
                        if (A[j] != previousNumber) {
                            indexFirst = j + 1
                            sliceSize = i - indexFirst
                            set.add(previousNumber)
                            set.add(A[i])
                            indexFirst = i
                            break
                        }
                    }
                } else {
                    i = ++indexFirst
                }
            }
        }
        updateLongest()

        return longest
    }
}
