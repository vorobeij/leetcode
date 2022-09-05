package leetcode.concurrency.channels

import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import leetcode.concurrency.threads.maxH
import leetcode.concurrency.threads.maxO
import leetcode.concurrency.threads.maxP

class H3P2OChannels {

    val outputFlow = MutableSharedFlow<Boolean>() // marker events to show molecule is ready
    private val channelH = Channel<suspend () -> Unit>()
    private val channelO = Channel<suspend () -> Unit>()
    private val channelP = Channel<suspend () -> Unit>()

    suspend fun collect(molecules: Int) = coroutineScope {
        var countMolecules = 0

        while (countMolecules < molecules) {
            countMolecules++
            val jobs = mutableListOf<Job>()
            jobs.addAll((1..maxH).map { _ -> launch { channelH.receive().invoke() } })
            jobs.addAll((1..maxO).map { _ -> launch { channelO.receive().invoke() } })
            jobs.addAll((1..maxP).map { _ -> launch { channelP.receive().invoke() } })
            jobs.joinAll()
            outputFlow.emit(true)
        }
    }

    suspend fun h(releaseHydrogen: suspend () -> Unit) {
        channelH.send(releaseHydrogen)
    }

    suspend fun o(releaseOxygen: suspend () -> Unit) {
        channelO.send(releaseOxygen)
    }

    suspend fun p(releaseP: suspend () -> Unit) {
        channelP.send(releaseP)
    }
}
