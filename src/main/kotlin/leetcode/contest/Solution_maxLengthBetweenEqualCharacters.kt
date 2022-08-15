package leetcode.contest

class Solution_maxLengthBetweenEqualCharacters {

    fun maxLengthBetweenEqualCharacters(s: String): Int {
        val chars = mutableMapOf<Char, Int>()
        val arr = s.toCharArray()
        arr.forEach {
            chars[it] = (chars[it] ?: 0) + 1
        }

        var max = -1

        for (i in 0..s.length - 2) {
            val c = s[i]
            if ((chars[c] ?: 0) > 1) {
                for (j in i + 1 until s.length) {
                    if (c == s[j]) {
                        val dist = j - i - 1
                        if (max < dist) max = dist
                    }
                }
                chars[c] = (chars[c] ?: 0) - 1
            }
        }

        return max
    }
}
