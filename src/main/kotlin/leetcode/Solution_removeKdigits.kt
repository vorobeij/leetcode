package leetcode

class Solution_removeKdigits {
    fun removeKdigits(num: String, k: Int): String {
        var toRemove = k
        var res = num
        while (toRemove > 0) {
            for (i in 0 until num.length - 1) {
                if (num[i].toInt() > num[i + 1].toInt()) {
                    res = res.replaceRange(i..i, "").trim('0')
                    break
                }
            }

            toRemove--
        }

        return res
    }
}
