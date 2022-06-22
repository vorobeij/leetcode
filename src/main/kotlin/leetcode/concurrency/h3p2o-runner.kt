package leetcode.concurrency

import concurrency.H3P2O

fun main() {
    val molecules = 10
    val totalO = molecules * 1
    val totalH = molecules * 3
    val totalP = molecules * 2

    val o = Runnable { print("O") }
    val p = Runnable { print("P") }
    val h = Runnable { print("H") }
    val h3P2O = H3P2O()

    val oProducer = Runnable {
        try {
            h3P2O.o(o)
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
    }
    val hProducer = Runnable {
        try {
            h3P2O.h(h)
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
    }
    val pProducer = Runnable {
        try {
            h3P2O.p(p)
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
    }

    repeat(totalO) { Thread(oProducer).start() }
    repeat(totalH) { Thread(hProducer).start() }
    repeat(totalP) { Thread(pProducer).start() }
}