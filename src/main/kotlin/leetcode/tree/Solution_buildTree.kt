package leetcode

import java.util.Stack

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null

    override fun toString(): String {
        val list = mutableListOf<Int?>()
        stringHelper(this, list)
        return list.joinToString(",")
    }

    private fun stringHelper(root: TreeNode?, buffer: MutableList<Int?>): List<Int?> {
        if (root == null) return buffer

        buffer.add(root.`val`)
        stringHelper(root.left, buffer)
        stringHelper(root.right, buffer)
        return buffer
    }
}

fun String.toTree(): TreeNode? {
    val values = split(",").map { if (it == "null") null else it.toInt() }.toMutableList()
    val treeNode = TreeNode(values.first()!!)
    values.removeAt(0)
    fun helper(root: TreeNode?, v: MutableList<Int?>) {
        if (v.isEmpty()) return
        root?.left = v[0]?.let { TreeNode(it) }
        values.removeAt(0)
        root?.right = v[0]?.let { TreeNode(it) }
        values.removeAt(0)
        root?.left.let { helper(it, v) }
        root?.right.let { helper(it, v) }
    }
    helper(treeNode, values)
    return treeNode
}

class Solution_buildTree {

    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        return TreeBuilder(preorder, inorder).build()
    }

    fun test(preorder: IntArray, inorder: IntArray): String {
        val root = TreeBuilder(preorder, inorder).build()
        val sb = StringBuilder()
        sb.append("[")
        preorder(root) {
            sb.append(it?.`val`.toString()).append(",")
        }
        sb.append("]")
        return sb.toString()
    }

    class TreeBuilder(
        private val preorder: IntArray, private val inorder: IntArray
    ) {

        var preorderIndex = 0
        fun build() = buildTree(0, inorder.size)

        private fun buildTree(inorderStart: Int, inorderEnd: Int): TreeNode? {
            if (inorderStart > inorderEnd || preorderIndex >= preorder.size) return null
            val root = TreeNode(preorder[preorderIndex++])
            if (inorderStart == inorderEnd) return root
            val inorderIndex = inorder.indexOf(root.`val`)

            root.left = buildTree(inorderStart, inorderIndex - 1)
            root.right = buildTree(inorderIndex + 1, inorderEnd)

            return root
        }
    }

    fun inorder(root: TreeNode?, onEach: (node: TreeNode?) -> Unit) {
        if (root == null) return

        val stack = Stack<TreeNode>()
        var curr: TreeNode? = root
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

    fun preorder(root: TreeNode?, onEach: (node: TreeNode?) -> Unit) {

        val q = mutableListOf<TreeNode?>()
        q.add(root)
        while (q.isNotEmpty()) {

            val node = q.first()
            q.removeFirst()
            onEach(node)
            if (node != null) {
                q.add(node.left)
                q.add(node.right)
            }
        }
    }
}
