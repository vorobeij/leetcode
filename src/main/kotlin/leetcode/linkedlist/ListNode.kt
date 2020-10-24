package leetcode.linkedlist

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

fun List<Int>.toNodesReverced(): ListNode {
    val res = ListNode(last())
    val r = reversed()
    var node: ListNode? = res
    for (i in 1 until size) {
        node?.next = ListNode(r[i])
        node = node?.next
    }
    return res
}

fun ListNode.last(): ListNode {
    var r: ListNode = this
    while (r.next != null) {
        if (r.next != null)
            r = r.next!!
    }
    return r
}

fun List<Int>.toNodes(): ListNode? {
    if (this.isEmpty()) return null
    val res = ListNode(first())
    val r = this
    var node: ListNode? = res
    for (i in 1 until size) {
        node?.next = ListNode(r[i])
        node = node?.next
    }
    return res
}

fun ListNode.string(): String {
    val list = mutableListOf<Int>()
    var n: ListNode? = this
    list.add(`val`)
    while (n?.next != null) {
        list.add(n.next!!.`val`)
        n = n.next
    }
    return "[${list.joinToString(",")}]"
}