package leetcode.concurrency.threads

import kotlin.system.measureTimeMillis

val molecules = 10

val maxO = 1
val maxH = 3
val maxP = 2

val totalO = molecules * maxO
val totalH = molecules * maxH
val totalP = molecules * maxP

fun main() {
    val t = measureTimeMillis {
        val sb = StringBuilder()

        val o = Runnable { sb.append("O") }
        val p = Runnable { sb.append("P") }
        val h = Runnable { sb.append("H") }
        val h3P2O = H3P2O {
            println(sb.toString().toCharArray().sorted())
            sb.clear()
        }

        val oProducer = createRunnable { h3P2O.o(o) }
        val hProducer = createRunnable { h3P2O.h(h) }
        val pProducer = createRunnable { h3P2O.p(p) }

        val threads = mutableListOf<Thread>()
        threads.addAll(runn(totalO, oProducer))
        threads.addAll(runn(totalH, hProducer))
        threads.addAll(runn(totalP, pProducer))
        threads.forEach { it.join() }
    }
    println(t)
}

private fun runn(repeat: Int, producer: Runnable): List<Thread> {
    return (0..repeat).map {
        Thread(producer).apply {
            start()
        }
    }
}

private inline fun createRunnable(crossinline code: () -> Unit): Runnable {
    return Runnable {
        try {
            code.invoke()
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
    }
}
