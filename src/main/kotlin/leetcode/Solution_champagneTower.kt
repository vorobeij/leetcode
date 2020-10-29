package leetcode

class Solution_champagneTower {

    private class Node(var value: Double) {
        var parent: Node? = null
        var left: Node? = null
        var right: Node? = null

        /* add one cup */
        fun add(add: Double) {
            val total = value + add
            when {
                total > 1 -> {
                    value = 1.0
                    val oneGlassPart = (total - 1) / 2
                    left?.add(oneGlassPart)
                    right?.add(oneGlassPart)
                }
                else -> {
                    value = total
                }
            }
        }
    }

    fun champagneTower(poured: Int, query_row: Int, query_glass: Int): Double {

        if (poured > (query_row + 1) * (query_row + 2)) return 1.0

        val head = Node(0.0)
        buildSubGraph(head, query_row)

        repeat(poured) {
            head.add(1.0)
            log("$it: ")
            printNode(head, 0)
        }

        var node: Node? = head
        repeat(query_glass) { node = node?.right }
        repeat(query_row - query_glass) { node = node?.left }
        return node?.value!!
    }

    private fun printNode(root: Node, level: Int) {
        val q = mutableListOf<Node>()
        q.add(root)

        while (q.isNotEmpty()) {

        }
    }

    fun log(m: Any?) {
        println(m.toString())
    }

    private fun buildSubGraph(root: Node, rows: Int) {
        if (rows == 0) return

        val leftBrother = root.parent?.left?.right
        root.left = (leftBrother ?: Node(0.0)).apply { parent = root }
        buildSubGraph(root.left!!, rows - 1)

        root.right = Node(0.0).apply { parent = root }
        buildSubGraph(root.right!!, rows - 1)
    }
}