package leetcode

class Solution_sortColors {

    fun sortColors(nums: IntArray) {
        val map = HashMap<Int, Int>()
        for (num in nums) {
            map[num] = (map[num] ?: 0) + 1
        }
        var lastIndex = 0
        map.entries.sortedBy { it.key }
            .forEach { entry ->
                for (i in lastIndex until lastIndex + entry.value) {
                    nums[i] = entry.key
                }
                lastIndex += entry.value
            }
    }
}
