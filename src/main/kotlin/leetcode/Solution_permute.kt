package leetcode

class Solution_permute {

    fun permute(nums: IntArray): List<List<Int>> {
        if (nums.isEmpty()) return emptyList()
        var permutations = mutableListOf(listOf(nums[0]))
        var i = 1

        while (i < nums.size) {
            val permsTemp = mutableListOf<List<Int>>()
            for (p in permutations) {
                println(p.joinToString())

                for (j in 0..p.size) {
                    val perm_j = p.toMutableList()
                    perm_j.add(j, nums[i])
                    permsTemp.add(perm_j)
                    println(permsTemp.joinToString())
                }
            }
            permutations = permsTemp
            i++
        }

        return permutations.sortedBy { it.joinToString() }
    }
}
