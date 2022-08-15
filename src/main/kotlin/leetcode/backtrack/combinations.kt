package leetcode.backtrack

fun main() {
    println(SolutionCombinations().combine(4, 2).size)
}

class SolutionCombinations {
    fun combine(n: Int, k: Int): List<List<Int>> {
        val res = hashSetOf<List<Int>>()
        backtrack(res, n, k, 1)
        return res.toList()
    }

    fun backtrack(
        res: HashSet<List<Int>>,
        n: Int,
        k: Int,
        left: Int = 1,
        currentList: MutableList<Int> = mutableListOf(left)
    ) {
        if (currentList.size == k) {
            if (res.contains(currentList)) {
                return
            }

            res.add(currentList)
        }
        if (left <= n - k) {
            for (i in left + 1..n) {
                // create new lists
                backtrack(res, n, k, i)
            }
        }
        if (currentList.size < k) {
            for (i in left + 1..n) {
                // add items to the current list
                val list = mutableListOf<Int>()
                list.addAll(currentList)
                list.add(i)
                backtrack(res, n, k, i, list)
            }
        }
    }
}
