package leetcode

class Solution_letterCombinations {

    val buttonsMatches = listOf(
        "abc",
        "def",
        "ghi",
        "jkl",
        "mno",
        "pqrs",
        "tuv",
        "wxyz"
    )

    fun letterCombinations(digits: String): List<String> {
        if (digits.isEmpty()) return emptyList()

        val buttons = mutableListOf<String>()
        for (digit in digits) {
            buttons.add(buttonsMatches[digit.toString().toInt() - 2])
        }
        val n = digits.length

        var res = buttons[0].toCharArray().map { it.toString() }
        for (level in 1 until n) {
            val temp = mutableListOf<String>()
            for (s in res) { // we have all combinations from previous levels here
                for (letter in buttons[level]) {
                    temp.add(s + letter)
                }
            }
            res = temp
        }

        return res
    }
}