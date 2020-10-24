package leetcode.linkedlist

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution_OddEvenLinkedList {
    fun oddEvenList(head: ListNode?): ListNode? {
        val h = head
        var odds = head
        var evens = head?.next
        val evensStart = head?.next

        while (evens?.next != null) {
            odds?.next = evens.next
            odds = odds?.next
            evens.next = odds?.next
            odds?.next = evensStart
            evens = evens.next
        }

        return h
    }
}