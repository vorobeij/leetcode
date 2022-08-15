package leetcode

import java.util.*

class BinaryTree {

    class Node(val value: Int) {
        var left: Node? = null
        var right: Node? = null
    }

    var root: Node? = null

    fun inorder(onEach: (node: Node?) -> Unit) {
        if (root == null) return

        val stack = Stack<Node>()
        var curr: Node? = root
        while (curr != null || stack.size > 0) {
            while (curr != null) {
                stack.push(curr)
                curr = curr.left
            }
            curr = stack.pop()
            onEach(curr)
            curr = curr.right
        }
    }

    fun breadth(onEach: (node: Node?) -> Unit) {

        val q = mutableListOf<Node?>()
        q.add(root)
        while (q.isNotEmpty()) {

            val node = q.first()
            q.removeFirst()
            onEach(node)
            node?.left?.let { q.add(it) }
            node?.right?.let { q.add(it) }
        }
    }

    fun depth(onEach: (node: Node?) -> Unit) {

    }
}

object Solution_BT {

    @JvmStatic
    fun main(args: Array<String>) {
        val bt = BinaryTree()
        bt.root = BinaryTree.Node(1)
        var cur = bt.root
        cur!!.left = BinaryTree.Node(2)
        cur.left!!.left = BinaryTree.Node(4)
        cur.left!!.right = BinaryTree.Node(5)
        cur.right = BinaryTree.Node(3)
        cur.right!!.left = BinaryTree.Node(6)
        cur.right!!.right = BinaryTree.Node(7)

        //        bt.inorder { println(it?.value.toString()) }
//        bt.breadth { println(it?.value.toString()) }
        bt.depth { println(it?.value.toString()) }
    }
}