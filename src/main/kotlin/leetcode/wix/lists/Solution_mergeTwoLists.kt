package leetcode.wix.lists

import leetcode.linkedlist.ListNode

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution_mergeTwoLists {

    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        if (list1 == null) return list2
        if (list2 == null) return list1

        // here we have at lease 1 element at each of lists
        val root = if (list1.`val` < list2.`val`) list1 else list2
        var node1: ListNode?
        var node2: ListNode?
        if (list1.`val` >= list2.`val`) {
            node1 = list1
            node2 = list2
        } else {
            node2 = list1
            node1 = list2
        }

        while (node2 != null) {
            while (node2?.next != null && (node2.next?.`val` ?: Int.MIN_VALUE) < (node1?.`val` ?: Int.MIN_VALUE)) {
                node2 = node2.next
            }
            node2!!
            when {
                node2.next == null -> {
                    node2.next = node1
                    return root
                }
                node2.`val` <= (node1?.`val` ?: Int.MIN_VALUE) -> {
                    val endNode1 = nextItem(node2.next?.`val`, node1)
                    endNode1 ?: return root
                    val list2End = node2.next
                    val list1End = endNode1.next
                    node2.next = node1
                    endNode1.next = list2End

                    node2 = list2End
                    node1 = list1End
                }
                else -> {
                    node2 = node2.next
                }
            }
        }
        return root
    }

    private fun nextItem(n: Int?, node: ListNode?): ListNode? {
        if (node == null || n == null) return node
        var link = node
        while (link != null && link.`val` <= n && link.next != null && (link.next?.`val` ?: Int.MAX_VALUE) <= n) link = link.next
        return link
    }
}

