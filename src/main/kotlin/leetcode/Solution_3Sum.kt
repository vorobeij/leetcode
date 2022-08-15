package leetcode

class Solution_3Sum {

    companion object {
        fun <T> List<T>.distinctN(n: Int): List<T> {
            val res = mutableListOf<T>()
            var i = 0
            while (i < size) {
                var j = i
                while (j < size && this[i] == this[j]) j++
                if (i != j) {
                    if (this[i] == 0) {
                        val items = (0 until 3.coerceAtMost((j - i))).map { this[i + it] }
                        res.addAll(items)
                    } else {
                        val items = (0 until n.coerceAtMost(j - i)).map { this[i + it] }
                        res.addAll(items)
                    }

                    i = j
                } else {
                    i++
                }
            }
            return res
        }
    }

    fun threeSum(nums: IntArray): List<List<Int>> {
        val results = mutableSetOf<List<Int>>()

        val sorted = nums.sorted()
        val numsSorted = sorted.distinctN(2)

        for (i in 0..numsSorted.size - 3) {
            for (j in (i + 1)..(numsSorted.size - 2)) {
                if (numsSorted[j] == numsSorted[j - 1] && numsSorted[j] != numsSorted[i]) continue
                val toFind = -(numsSorted[i] + numsSorted[j])
                val k = numsSorted.binarySearch(toFind, j + 1, numsSorted.size)
                if (k > -1) {
                    results.add(listOf(numsSorted[i], numsSorted[j], numsSorted[k]))
                }
            }
        }

        return results.toList()
    }
}
