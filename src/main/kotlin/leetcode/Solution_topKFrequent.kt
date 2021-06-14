package leetcode

class Solution_topKFrequent {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val map = HashMap<Int, Int>()
        for (num in nums) {
            map[num] = (map[num] ?: 0) + 1
        }
        return map.entries.sortedByDescending { it.value }.map{it.key}.take(k).toIntArray()
    }
}