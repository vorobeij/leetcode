package leetcode

class Solution_subsets {

    fun subsets(nums: IntArray): List<List<Int>> {
        val arr = nums.mapIndexed { index, it -> index }
        val out = mutableListOf<List<Int>>()
        out.add(listOf())
        out.addAll(arr.map { listOf(it) })
        arr.forEach { traverse(nums, listOf(it), out) }
        return out.map { it.map { nums[it] } }
    }

    fun traverse(nums: IntArray, current: List<Int>, output: MutableList<List<Int>>) {
        for (i in current.last() + 1 until nums.size) {
            val arr = current.toMutableList()
            arr.add(i)
            output.add(arr)
            traverse(nums, arr, output)
        }
    }
}