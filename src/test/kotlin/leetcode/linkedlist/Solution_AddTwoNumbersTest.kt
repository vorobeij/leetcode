package leetcode.linkedlist

import com.google.common.truth.Truth
import org.junit.Test

class Solution_AddTwoNumbersTest {

    val t = Solution_AddTwoNumbers()

    private fun List<Int>.toNodes(): Solution_AddTwoNumbers.ListNode {
        val res = Solution_AddTwoNumbers.ListNode(last())
        val r = reversed()
        var node: Solution_AddTwoNumbers.ListNode? = res
        for (i in 1 until size) {
            node?.next = Solution_AddTwoNumbers.ListNode(r[i])
            node = node?.next
        }
        return res
    }

    fun Solution_AddTwoNumbers.ListNode.string(): String {
        val list = mutableListOf<Int>()
        var n: Solution_AddTwoNumbers.ListNode? = this
        list.add(`val`)
        while (n?.next != null) {
            list.add(n.next!!.`val`)
            n = n.next
        }
        return "[${list.joinToString(",")}]"
    }

    @Test
    fun `test 1`() {

        Truth.assertThat(listOf(1, 2, 3).toNodes().string()).isEqualTo("[3,2,1]")
//        Truth.assertThat(t.addTwoNumbers(listOf(3, 2, 1).toNodes(), listOf(3, 2, 1).toNodes())?.string())
//            .isEqualTo("[2,4,6]")
        Truth.assertThat(t.addTwoNumbers(listOf(9, 9).toNodes(), listOf(3).toNodes())?.string()).isEqualTo("[2,0,1]")
    }
}