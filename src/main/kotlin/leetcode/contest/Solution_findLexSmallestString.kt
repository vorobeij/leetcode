package leetcode.contest

class Solution_findLexSmallestString {

    fun rotate(s: String, b: Int): String {
        return s.substring(b, s.length) + s.substring(0, b)
    }

    fun add(s: String, a: Int): String {
        return s.toCharArray()
            .mapIndexed { index, c ->
                if (index % 2 != 0) (c.toString().toInt() + a) % 10 else c
            }
            .joinToString("")
    }

    private val set = HashSet<String>()
    var smallest = ""

    fun findLexSmallestString(s: String, a: Int, b: Int): String {
        smallest = s
        find(s, a, b)
        return smallest
    }

    private fun find(s: String, a: Int, b: Int) {
        if (set.contains(s)) return

        if (s < smallest) {
            smallest = s
        }

        set.add(s)

        find(rotate(s, b), a, b)
        find(add(s, a), a, b)
    }
}