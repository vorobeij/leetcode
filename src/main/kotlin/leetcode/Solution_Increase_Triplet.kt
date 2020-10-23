package leetcode

class Solution_Increase_Triplet {

    fun increasingTriplet(nums: IntArray): Boolean {

        if (nums.isEmpty() || nums.size < 3) return false

        var min1 = Int.MAX_VALUE
        var min2 = Int.MAX_VALUE

        for (n in nums) {
            if (n < min1) min1 = n
            if (n > min1) min2 = Math.min(min2, n)
            if (n > min2) return true
        }

        return false
    }
}