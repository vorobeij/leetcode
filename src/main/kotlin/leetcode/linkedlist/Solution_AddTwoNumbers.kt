package leetcode.linkedlist

class Solution_AddTwoNumbers {

    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val node = ListNode(0)
        add(l1, l2, node)
        return node
    }

    fun add(l1: ListNode?, l2: ListNode?, res: ListNode): ListNode? {
        if (l1 == null && l2 == null) return res
        val sum = (l1?.`val` ?: 0) + (l2?.`val` ?: 0)
        val rem = sum % 10
        var decimals = sum / 10
        res.`val` += rem
        if (res.`val` >= 10) {
            res.`val` -= 10
            decimals++
        }

        if (l1?.next != null || l2?.next != null) {
            val next = ListNode(decimals)
            res.next = next
            return add(l1?.next, l2?.next, next)
        } else {
            if (decimals > 0) {
                val next = ListNode(decimals)
                res.next = next
                return next
            }
            return res
        }
    }
}