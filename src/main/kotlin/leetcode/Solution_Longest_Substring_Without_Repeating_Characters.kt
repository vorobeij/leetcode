package leetcode

class Solution_Longest_Substring_Without_Repeating_Characters {

    fun lengthOfLongestSubstring(s: String): Int {

        var longestSubstring = mutableListOf<Char>()
        var currentSubstring = mutableListOf<Char>()

        var i = 0
        while (i < s.length) {

            if (currentSubstring.contains(s[i])) {
                i = i - currentSubstring.size
                if (currentSubstring.size > longestSubstring.size) {
                    longestSubstring = currentSubstring
                }
                currentSubstring = mutableListOf()
            } else {
                currentSubstring.add(s[i])
            }
            i++
        }

        if (currentSubstring.size > longestSubstring.size) {
            longestSubstring = currentSubstring
        }

        return longestSubstring.size
    }
}