package leetcode.tree

import leetcode.TreeNode

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {

    private var max = Int.MIN_VALUE
    fun maxPathSum(root: TreeNode?): Int {
        max = Int.MIN_VALUE
        traverseTree(root)
        return max
    }

    private fun traverseTree(root: TreeNode?): Int {
        if (root == null) return 0

        val left = traverseTree(root.left)
        val right = traverseTree(root.right)
        val max2 = mutableListOf(
                right + root.`val`,
                left + root.`val`,
                root.`val`
            ).maxOrNull()!!

        max = mutableListOf(
                left + right + root.`val`,
                max2,
                max
            ).maxOrNull()!!
        return max2
    }
}