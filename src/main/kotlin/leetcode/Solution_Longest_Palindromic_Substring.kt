package leetcode

class Solution_Longest_Palindromic_Substring {

    fun isPalindrome(s: String, start: Int, end: Int): Boolean {
        var i = 0
        while (start + i < end - i && s[start + i] == s[end - i]) {
            i++
        }
        if ((end - start) % 2 == 0) {
            return i == (end - start) / 2
        } else {
            return i == (end - start) / 2 + 1
        }
    }

    fun longestPalindrome(s: String): String {
        var palindrome: String = ""

        for (i in s.indices) {
            for (j in i until s.length) {
                if (s[i] == s[j] && isPalindrome(s, i, j)) {
                    if (palindrome.length < j - i + 1) {
                        palindrome = s.substring(i, j + 1)
                    }
                }
            }
        }

        return palindrome
    }
}
