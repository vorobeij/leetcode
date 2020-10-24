package leetcode.linkedlist

class Solution_Intersection_of_Two_Linked_Lists {

    fun ListNode?.size(): Int {
        var h = this
        var i = 0
        while (h != null) {
            i++
            h = h.next
        }
        return i
    }

    fun ListNode?.get(i: Int): ListNode? {
        var j = 0
        var res = this
        while (j < i) {
            j++
            res = res?.next
        }
        return res
    }

    fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
        val sizeA = headA.size()
        val sizeB = headB.size()

        var hA = headA.get(sizeA - Math.min(sizeA, sizeB))
        var hB = headB.get(sizeB - Math.min(sizeA, sizeB))

        while (hA?.next != null && hB?.next != null) {
            if (hA?.next == hB?.next)
                return hA?.next
            hA = hA?.next
            hB = hB?.next
        }

        return null
    }
}