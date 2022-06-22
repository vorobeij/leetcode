package leetcode.linkedlist


/**
 * Example:
 * var ti = Node(5)
 * var v = ti.`val`
 * Definition for a Node.
 * class Node(var `val`: Int) {
 *     var next: Node? = null
 *     var random: Node? = null
 * }
 */
class Node(var `val`: Int) {
    var next: Node? = null
    var random: Node? = null
}

/**
 * Example:
 * var ti = Node(5)
 * var v = ti.`val`
 * Definition for a Node.
 * class Node(var `val`: Int) {
 *     var next: Node? = null
 *     var random: Node? = null
 * }
 */

class Solution {
    fun copyRandomList(node: Node?): Node? {
        // indices of random nodes
        val randomIndices = mutableListOf<Int>()
        iterate(node) { indexNode ->
            val randomNode: Node? = indexNode.random
            if (randomNode == null) {
                randomIndices.add(-1)
            } else {
                var indexOfRandomNode = 0
                var iteratorNode = node
                while (iteratorNode != randomNode) {
                    indexOfRandomNode++
                    iteratorNode = iteratorNode?.next
                }
                randomIndices.add(indexOfRandomNode)
            }
        }

        // create copy and link next nodes
        val nodeCopy = node?.copy()
        var indexNodeCopy = nodeCopy

        iterate(node) { indexNode ->
            indexNodeCopy?.next = indexNode.next?.copy()
            indexNodeCopy = indexNodeCopy?.next
        }

        // apply random nodes
        var i = 0
        iterate(nodeCopy) { indexNode ->
            val randomIndex = randomIndices[i]
            if (randomIndex == -1) {
                indexNode.random = null
            } else {
                var j = 0
                var randomNode: Node? = nodeCopy
                while (j <= randomIndex) {
                    randomNode = randomNode?.next
                    j++
                }
                indexNode.random = randomNode
            }
        }

        return nodeCopy
    }

    private fun Node.copy() = Node(`val`)

    private fun iterate(node: Node?, action: (indexNode: Node) -> Unit) {
        var indexNode = node
        while (indexNode != null) {
            action.invoke(indexNode)
            indexNode = indexNode.next
        }
    }
}