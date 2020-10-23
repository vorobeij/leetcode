package leetcode

class Solution_GroupAnagrams {

    fun groupAnagrams(strs: Array<String>): List<List<String>> {

        val map = mutableMapOf<String, MutableList<String>>()
        strs.forEach {
            val key = it.toCharArray().sorted().joinToString()
            println(key)
            var list = map[key]
            if (list == null) {
                list = mutableListOf()
                map[key] = list
            }
            list.add(it)
        }

        return map.values.toList()
    }
}