package leetcode.concurrency.channels

import kotlin.system.measureTimeMillis
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import leetcode.concurrency.threads.molecules
import leetcode.concurrency.threads.totalH
import leetcode.concurrency.threads.totalO
import leetcode.concurrency.threads.totalP

fun main() = runBlocking {
    val t = measureTimeMillis {
        val sb = StringBuilder()
        val h3P2O = H3P2OChannels()
        val collectionJob: Job = h3P2O.outputFlow
            .onEach {
                println(sb.toString().toCharArray().sorted().joinToString(""))
                sb.clear()
            }
            .onCompletion { println("done") }
            .launchIn(this)

        val jobs = mutableListOf<Job>()
        jobs.addAll(runx(totalH) { h3P2O.h { delay(100); sb.append("H") } })
        jobs.addAll(runx(totalO) { h3P2O.o { delay(10); sb.append("O") } })
        jobs.addAll(runx(totalP) { h3P2O.p { delay(50); sb.append("P") } })
        h3P2O.collect(molecules)
        jobs.joinAll()
        collectionJob.cancel()
    }
    println(t)
}

private fun CoroutineScope.runx(total: Int, function: suspend () -> Unit): List<Job> {
    return (1..total).map {
        launch {
            function.invoke()
        }
    }
}
